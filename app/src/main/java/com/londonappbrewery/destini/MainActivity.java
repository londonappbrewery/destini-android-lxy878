package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.end;
import static android.R.attr.id;
import static android.R.id.button2;

public class MainActivity extends AppCompatActivity {

    private class Context{
        private int story;
        private int answer1;
        private int answer2;

        public Context(int idStory, int idAnswer1, int idAnswer2){
            story = idStory;
            answer1 = idAnswer1;
            answer2 = idAnswer2;
        }

        public Context(int idStory){
            answer1 = 0;
            story = idStory;
            answer2 = 0;
        }

        public int GetStory(){
            return story;
        }
        public int GetAnswer1(){
            return answer1;
        }
        public int GetAnswer2(){
            return answer2;
        }
    }

    // TODO: Steps 4 & 8 - Declare member variables here:

    public Context [] stories = new Context[] {
            new Context (R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2),
            new Context (R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2),
            new Context (R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2)
    };
    public Context [] endings = new Context[] {
            new Context (R.string.T4_End),
            new Context (R.string.T5_End),
            new Context (R.string.T6_End)
    };

    private TextView story;
    private Button button1;
    private Button button2;
    private int storyIndex = 0;
    private boolean isEnd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:

        story = (TextView) findViewById(R.id.storyTextView);
        button1 = (Button) findViewById(R.id.buttonTop);
        button2 = (Button) findViewById(R.id.buttonBottom);

        Display();

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                int idAnswer = stories[storyIndex].GetAnswer1();
                NextContext(idAnswer);
                Display();
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick (View view){
                int idAnswer = stories[storyIndex].GetAnswer2();
                NextContext(idAnswer);
                Display();
            }
        });

    }

    public void NextContext(int idAnswer){
        Context currentStory = stories[storyIndex];
        switch (currentStory.GetStory()){
            case R.string.T1_Story:
                if(idAnswer == currentStory.GetAnswer1()){
                    storyIndex =+2;
                }else if(idAnswer == currentStory.GetAnswer2()){
                    storyIndex ++;
                }
                break;
            case R.string.T2_Story:
                if(idAnswer == currentStory.GetAnswer1()){
                    storyIndex++;
                }else if(idAnswer == currentStory.GetAnswer2()){
                    Ending(endings[0].GetStory());
                }
                break;
            case R.string.T3_Story:
                if(idAnswer == currentStory.GetAnswer1()){
                    Ending(endings[2].GetStory());
                }else if(idAnswer == currentStory.GetAnswer2()){
                    Ending(endings[1].GetStory());
                }
                break;
            default:
                return;
        }
    }
    public void Ending(int idEnding){
        isEnd = true;
        if(idEnding == endings[0].GetStory())
            story.setText(endings[0].GetStory());
        else if (idEnding == endings[1].GetStory())
            story.setText(endings[1].GetStory());
        else if (idEnding == endings[2].GetStory())
            story.setText(endings[2].GetStory());
        ViewGroup parentButton1 = (ViewGroup) button1.getParent();
        parentButton1.removeView(button1);
        ViewGroup parentButton2 = (ViewGroup) button2.getParent();
        parentButton2.removeView(button2);
    }
    public void Display() {
        if(isEnd){
            return;
        }
        story.setText(stories[storyIndex].GetStory());
        button1.setText(stories[storyIndex].GetAnswer1());
        button2.setText(stories[storyIndex].GetAnswer2());
    }
}
