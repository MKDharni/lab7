package com.example.androidlabs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.androidlabs.Database.COLUMN_ID;
import static com.example.androidlabs.Database.COLUMN_MESSAGE;


public class ChatRoomActivity extends AppCompatActivity
{
    private ChatAdapter myAdapter;
    private EditText chatField;
    //private String value;
    List<Message> messageList = new ArrayList<>();
    Button SendingButton;
    Button ReceivingButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        listView = (ListView) findViewById(R.id.listView);
        chatField = (EditText) findViewById(R.id.msgBox);
        SendingButton = (Button) findViewById(R.id.buttonSend);
        ReceivingButton = (Button) findViewById(R.id.buttonReceive);
        myAdapter= new ChatAdapter(messageList,this);
        Database dbase = new Database(this);
        SQLiteDatabase db = dbase.getWritableDatabase();
        String[] columns = {COLUMN_ID, Database.COLUMN_SEND, COLUMN_MESSAGE};
        String tableName = Database.TABLE_NAME;
        Cursor mainQuery = db.query(false, tableName, columns, null, null, null, null, null, null);
        dbase.printCursor(mainQuery);
        int isSendColumnIndex = mainQuery.getColumnIndex(Database.COLUMN_SEND);
        int messageColIndex = mainQuery.getColumnIndex(COLUMN_MESSAGE);
        int idColIndex = mainQuery.getColumnIndex(COLUMN_ID);
        while (mainQuery.moveToNext()) {
            int isSendInt = mainQuery.getInt(isSendColumnIndex);
            boolean isSent = false;
            if (isSendInt == 1) {
                isSent = true;
            }
            String theMessage = mainQuery.getString(messageColIndex);
            Long id = mainQuery.getLong(idColIndex);
            messageList.add(new Message(theMessage, isSent, id));
        }
        listView.setAdapter(myAdapter);

            SendingButton.setOnClickListener(click -> {
            Message message = new Message(chatField.getText().toString(),true);
            ContentValues newValues = new ContentValues();
            newValues.put(COLUMN_MESSAGE, message.getMessage());
            newValues.put(Database.COLUMN_SEND, message.isGone());
            long newId = db.insert(Database.TABLE_NAME, null, newValues);
            message.messageID = newId;
            messageList.add(message);
            chatField.setText("");
            myAdapter.notifyDataSetChanged();
            listView.setAdapter(myAdapter);
        });


        ReceivingButton.setOnClickListener(click -> {
            Message message = new Message(chatField.getText().toString(),false);
            ContentValues newValues = new ContentValues();
            newValues.put(COLUMN_MESSAGE, message.getMessage());
            newValues.put(Database.COLUMN_SEND, message.isGone());
            long longID = db.insert(Database.TABLE_NAME, null, newValues);
            message.messageID = longID;
             messageList.add(message);
            chatField.setText("");
            myAdapter.notifyDataSetChanged();
            listView.setAdapter(myAdapter); });

        listView.setOnItemLongClickListener((AdapterView<?> parent, View view, int position, long id) ->
        {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ChatRoomActivity.this);
            alertBuilder.setTitle("Are you sure you want to delete?");
            alertBuilder.setMessage("The data will be deleted.Do you want to continue?" +
                    "+\n "+"The position/row of the chat is: "+ position+"\n"+"The id is: "+id);
            alertBuilder.setPositiveButton("Yes", (DialogInterface dialog, int num) ->
            {
            Message messageToRemove = messageList.get(position);
            db.delete(Database.TABLE_NAME, COLUMN_ID+"= ?",new String[]{Long.toString(messageToRemove.getMessageID())});
            messageList.remove(position);
            listView.setAdapter(myAdapter);
            });
            alertBuilder.setNegativeButton("No", null);
            alertBuilder.show();
            return true;});
            Log.d("ChatRoomActivity", "onCreate");}
}







