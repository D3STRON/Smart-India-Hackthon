package com.example.sihagriculture;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Ankit on 3/30/2018.
 */

public class ChatActivity extends AppCompatActivity {
    ChatView chatView;
    EditText et;
    ImageButton sendButton;
    Socket mSocket = null;
    int stop = 0;
    JSONObject erer;
    static ArrayList<Message> list;
    public static final String BASE_URL = "http://172.28.24.103:3000/";
    int c= 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        ReadPrev readPrev = new ReadPrev(this);
        readPrev.execute();

        list = new ArrayList<Message>();
        try {
            mSocket = IO.socket(BASE_URL);
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        chatView = (ChatView) findViewById(R.id.chat_view);
        et = (EditText) findViewById(R.id.editTextToSend);
        sendButton = (ImageButton) findViewById(R.id.fib);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message message = new Message(et.getText().toString(),
                        Message.Type.SENT, "Ankit", PrefUtils.getString(ChatActivity.this, "aadhar", "123456789101"));
                chatView.addMessage(message);

                erer = new JSONObject();
                try {
                    erer.put("username", "Ankit");
                    erer.put("msg", et.getText().toString());
                    erer.put("_id", PrefUtils.getString(ChatActivity.this, "aadhar", "123456789101"));
                    et.setText("");
                    if (mSocket != null) {
                        if (stop == 0) {
                            Log.d("mSocketCheck", "query");
                            mSocket.emit("query", erer);
                        } else {
                            mSocket.emit("send", erer);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


        mSocket.on("receive", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                final JSONObject jbfd = (JSONObject) args[0];


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            c++;
                            Message message = new Message(jbfd.get("msg").toString(),
                                    Message.Type.RECEIVED, jbfd.get("username").toString(),
                                    PrefUtils.getString(getApplication(), "aadhar", "1234567891011"));
                            chatView.addMessage(message);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        }

                });

            }
        });

        mSocket.on("response", new Emitter.Listener() {
            @Override
            public void call(Object... argsff) {

                final JSONObject asd = (JSONObject) argsff[0];
                //   Log.d("andandjs", asd.toString());

                try {
                    if (Double.parseDouble(asd.get("maxVal").toString()) < 0.3f) {
                        //send to scientist

                        stop = 1;
                        mSocket.off("query");
                        mSocket.emit("send", erer);
                        // mSocket.off("response");

                    } else {
                        //correct and display to the main thread

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    //  mSocket.emit("send", erer);
                                    Message message = new Message(asd.get("question").toString(),
                                            Message.Type.RECEIVED, asd.get("answer").toString(),
                                            PrefUtils.getString(getApplication(), "aadhar", "1234567891011"));
                                    chatView.addMessage(message);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }

    public static ArrayList readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray jsonArray = new JSONArray(jsonText);

            JSONObject jsonObjefsj = new JSONObject(jsonArray.get(0).toString());
            JSONArray jkasn = new JSONArray(jsonObjefsj.getString("chat"));

            for (int n = 0; n < jkasn.length(); n++) {
                JSONObject object = jkasn.getJSONObject(n);
                //check the user names

                Message message = new Message(object.get("msg").toString(), Message.Type.SENT, object.get("username").toString(), object.getString("_id"));

                list.add(message);
              //  Log.d("bahdha", message.getMessage());
            }

            return list;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    class ReadPrev extends AsyncTask<Void, Void, ArrayList<Message>> {
        ProgressDialog dialog;

        public ReadPrev(ChatActivity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {

            //
            // dialog.setMessage("Fetching, please wait.");
            // dialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<Message> mlist) {
            super.onPostExecute(mlist);
            for (Message m : mlist) {
                chatView.addMessage(m);
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected ArrayList<Message> doInBackground(Void... voids) {
            ArrayList<Message> previous_texts = new ArrayList<>();
            try {
                previous_texts = readJsonFromUrl(BASE_URL + "past/" + PrefUtils.getString(ChatActivity.this, "aadhar", "123456789101"));
                // Log.d("prev_resp", previous_texts.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return previous_texts;
        }
    }
}
