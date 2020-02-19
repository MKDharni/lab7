package com.example.androidlabs;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {
    private ChatAdapter myAdapter;
    private EditText chatField;
    private String value;


    List<MessageModel> messageList = new ArrayList<>();
    Button sButton;
    Button rButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


        sButton = (Button) findViewById(R.id.buttonReceive);
        rButton = (Button) findViewById(R.id.buttonReceive);
        listView = (ListView) findViewById(R.id.listView);
        chatField = (EditText) findViewById(R.id.msgBox);
        myAdapter= new ChatAdapter(messageList,this);


        MyDataOpener dbOpener = new MyDataOpener(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();
        String[] columns = {MyDataOpener.COLUMN_ID, MyDataOpener.COLUMN_SEND, MyDataOpener.COLUMN_MESSAGE};
        String tableName = MyDataOpener.TABLE_NAME;
        Cursor results = db.query(false, tableName, columns, null, null, null, null, null, null);

        dbOpener.printCursor(results);
        int isSendColumnIndex = results.getColumnIndex(MyDataOpener.COLUMN_SEND);
        int messageColIndex = results.getColumnIndex(MyDataOpener.COLUMN_MESSAGE);
        int idColIndex = results.getColumnIndex(MyDataOpener.COLUMN_ID);

        while (results.moveToNext()) {
            int isSendInt = results.getInt(isSendColumnIndex);
            boolean isSent = false;
            if (isSendInt == 1) {
                isSent = true;
            }
            String theMessage = results.getString(messageColIndex);

            Long id = results.getLong(idColIndex);
            messageList.add(new MessageModel(theMessage, isSent, id));
        }
        listView.setAdapter(myAdapter);


        sButton.setOnClickListener(click -> {
            MessageModel message = new MessageModel(chatField.getText().toString(),true);
            ContentValues newRowsValues = new ContentValues();
            newRowsValues.put(MyDataOpener.COLUMN_MESSAGE, message.getMessage());
            newRowsValues.put(MyDataOpener.COLUMN_SEND, message.isSend());
            long newId = db.insert(MyDataOpener.TABLE_NAME, null, newRowsValues);
            message.messageID = newId;

            messageList.add(message);
            chatField.setText("");
            myAdapter.notifyDataSetChanged();
            listView.setAdapter(myAdapter);
        });


        rButton.setOnClickListener(click -> {

            MessageModel message = new MessageModel(chatField.getText().toString(),false);
            ContentValues newRowsValues = new ContentValues();
            newRowsValues.put(MyDataOpener.COLUMN_MESSAGE, message.getMessage());
            newRowsValues.put(MyDataOpener.COLUMN_SEND, message.isSend());
            long newId = db.insert(MyDataOpener.TABLE_NAME, null, newRowsValues);
            message.messageID = newId;

            messageList.add(message);
            chatField.setText("");
            myAdapter.notifyDataSetChanged();
            listView.setAdapter(myAdapter);
        });

        listView.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ChatRoomActivity.this);
            alertBuilder.setTitle("Message Deletion");
            alertBuilder.setMessage("Message will be Deleted, do you want to continue ?");
            alertBuilder.setPositiveButton("Yes", (DialogInterface dialog, int num) -> {
                MessageModel messageToRemove = messageList.get(position);
                db.delete(MyDataOpener.TABLE_NAME,MyDataOpener.COLUMN_ID+"= ?",new String[]{Long.toString(messageToRemove.getMessageID())});
                messageList.remove(position);
                listView.setAdapter(myAdapter);

            });

            alertBuilder.setNegativeButton("No", null);
            alertBuilder.show();
            return true;
        });
        Log.d("ChatRoomActivity", "onCreate");
    }
}







