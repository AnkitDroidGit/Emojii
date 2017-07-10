package com.freeankit.emojii;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.freeankit.emojii.emoji.Actions.EmojIconActions;
import com.freeankit.emojii.emoji.Helper.EmojIconTextView;
import com.freeankit.emojii.emoji.Helper.EmojiconEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.show_emoji)
    EmojIconTextView emojIconTextView;
    @BindView(R.id.chat_edit_text)
    EmojiconEditText emojiconEditText;
    @BindView(R.id.emoji)
    ImageView emoji;
    @BindView(R.id.parent_layout)
    RelativeLayout parentLayout;
    @BindView(R.id.send)
    ImageView sendChatEmoji;

    EmojIconActions emojIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        emojIcon = new EmojIconActions(this, parentLayout, emojiconEditText, emoji);
        emojIcon.ShowEmojIcon();
        emojIcon.setKeyboardListener(new EmojIconActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }

            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });

        emojiconEditText.setOnKeyListener(keyListener);
        sendChatEmoji.setOnClickListener(clickListener);
        emojiconEditText.setOnClickListener(clickListener_2);

    }

    private EditText.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            // If the event is a key-down event on the "enter" button
            if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // Perform action on key press

                EditText editText = (EditText) v;

                if (v == emojiconEditText) {
                    sendMessage(editText.getText().toString());
                }

                emojiconEditText.setText("");

                return true;
            }
            return false;
        }
    };

    private ImageButton.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == sendChatEmoji) {
                sendMessage(emojiconEditText.getText().toString());
            }
            emojiconEditText.setText("");

        }
    };

    private ImageButton.OnClickListener clickListener_2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };


    private void sendMessage(final String messageText) {
        if (messageText.trim().length() == 0)
            return;
        emojIconTextView.setText(messageText);
    }

}
