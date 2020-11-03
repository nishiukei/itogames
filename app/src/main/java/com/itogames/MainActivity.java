package com.itogames;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> q_number = new ArrayList<>();
    private ArrayList<String> players = new ArrayList<>();
    private ArrayList<Integer> answer = new ArrayList<>();
    private ArrayList<Integer> order = new ArrayList<>();
    private ArrayList<Integer> answer_dis = new ArrayList<>();
    private int miss = 0;
    private int n = 0;
    private int num4_5 = 0;
    private int num5 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNumber(q_number);
        setScreenFirst();
    }

    public void setNumber(List<Integer> q_number){
        for(int i = 1; i <= 99; i++) {
            q_number.add(i);
        }
        Collections.shuffle(q_number);
    }

    public void setScreenFirst(){
        setContentView(R.layout.activity_main);

        Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setScreenSecond();
            }
        });
    }

    public void setScreenSecond(){
        setContentView(R.layout.second);

        Button button_2 = findViewById(R.id.button_2);
        Button button_player = findViewById(R.id.button_player);
        final EditText editText = findViewById(R.id.editText);

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < n; i++) {
                    if(i == 0) {
                        answer.add(q_number.get(0));
                        order.add(q_number.get(0));
                    }else if(i == 1) {
                        answer.add(q_number.get(1));
                        order.add(q_number.get(1));

                    }else if(i == 2) {
                        answer.add(q_number.get(2));
                        order.add(q_number.get(2));

                    }else if(i == 3) {
                        answer.add(q_number.get(3));
                        order.add(q_number.get(3));

                    }else if(i == 4) {
                        answer.add(q_number.get(4));
                        order.add(q_number.get(4));
                    }
                }

                setScreenThird();
            }
        });

        button_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_text = editText.getText().toString();
                if (editText.getText().toString().equals("")) {
                    editText.setError("名前を入力してください");
                } else {
                    if (n == 5) {
                        editText.setError("５人までしか遊べません");
                    } else {
                        players.add(n , input_text);
                        n++;
                        TextView game_player = findViewById(R.id.playerViews);
                        game_player.setText(n + "人参加中");
                        editText.setText("");
                    }
                }
            }
        });
    }

    public  void setScreenThird(){
        setContentView(R.layout.third);

        TextView game_player_view = findViewById(R.id.nameView);
        game_player_view.setText(players.get(num4_5));
        Button button_3 = findViewById(R.id.button_3);

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setScreenFourth();
            }
        });
    }

    public void setScreenFourth(){
        setContentView(R.layout.fourth);
        TextView player_number = findViewById(R.id.playerNumber);
        player_number.setText(String.valueOf(answer.get(num4_5)));
        Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num4_5 < n - 1) {
                    num4_5++;
                    setScreenThird();
                }else {
                    setScreenFifth();
                }
            }
        });
    }

    public void setScreenFifth(){
        setContentView(R.layout.fifth);
        setTextScreenPlayers();

        Collections.sort(order);
        final Button button5 = findViewById(R.id.button_5);
        final EditText edit = findViewById(R.id.editAnser);
        final TextView answer_text_view = findViewById(R.id.textView5);
        answer_text_view.setText(num5 + "番目に小さいと思う人の番号を入力してください");

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num5 > n){
                    setScreenSixth();
                } else {
                    if (edit.getText().toString().equals("")) {
                        edit.setError("有効な数字を入力してください");
                    } else {
                        answer_text_view.setText(num5 + 1 + "番目に小さいと思う人の番号を入力してください");
                        String edit_answer = edit.getText().toString();
                        int input_answer = Integer.parseInt(edit_answer);
                        edit.setText("");
                        if(num5 == n){
                            edit.setKeyListener(null);
                            button5.setText("結果発表");
                            button5.setBackgroundColor(Color.MAGENTA);
                            answer_text_view.setText("");
                            edit.setHint("入力を受け付けました！！");
                            setMissCounter(input_answer);
                        } else {
                            setMissCounter(input_answer);
                        }
                    }
                }
            }
        });
    }

    public void setMissCounter(int input_answer){
        if (answer.get(input_answer) == order.get(num5 - 1)) {
            answer_dis.add(input_answer);
            setTextScreenPlayersAnswer();
            num5++;
        } else {
            answer_dis.add(input_answer);
            setTextScreenPlayersAnswer();
            num5++;
            miss++;
        }
    }
    public void setTextScreenPlayers(){
        TextView player1 = findViewById(R.id.answerPlayer1);
        TextView player2 = findViewById(R.id.answerPlayer2);
        TextView player3 = findViewById(R.id.answerPlayer3);
        TextView player4 = findViewById(R.id.answerPlayer4);
        TextView player5 = findViewById(R.id.answerPlayer5);
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                player1.setText(i + " : " + players.get(i));
            }else if(i == 1) {
                player2.setText(i + " : " + players.get(i));
            }else if(i == 2) {
                player3.setText(i + " : " + players.get(i));
            }else if(i == 3) {
                player4.setText(i + " : " + players.get(i));
            }else if(i == 4) {
                player5.setText(i + " : " + players.get(i));
            }
        }
    }
    public void setTextScreenPlayerNumbers(){
        TextView number1 = findViewById(R.id.answerPlayerNumber1);
        TextView number2 = findViewById(R.id.answerPlayerNumber2);
        TextView number3 = findViewById(R.id.answerPlayerNumber3);
        TextView number4 = findViewById(R.id.answerPlayerNumber4);
        TextView number5 = findViewById(R.id.answerPlayerNumber5);
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                number1.setText(String.valueOf(answer.get(i)));
            }else if(i == 1) {
                number2.setText(String.valueOf(answer.get(i)));
            }else if(i == 2) {
                number3.setText(String.valueOf(answer.get(i)));
            }else if(i == 3) {
                number4.setText(String.valueOf(answer.get(i)));
            }else if(i == 4) {
                number5.setText(String.valueOf(answer.get(i)));
            }
        }
    }

    public void setTextScreenPlayersAnswer(){
        TextView text = findViewById(R.id.response);
        text.setText(String.valueOf(answer_dis));
    }

    public void setScreenSixth(){
        setContentView(R.layout.six);
        TextView text_miss = findViewById(R.id.textView6_1);
        text_miss.setText(String.valueOf(n - miss));
        setTextScreenPlayers();
        setTextScreenPlayerNumbers();
        setTextScreenPlayersAnswer();

        TextView text_show_answer = findViewById(R.id.showAnswer);
        text_show_answer.setText(String.valueOf(order));

        Button button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}