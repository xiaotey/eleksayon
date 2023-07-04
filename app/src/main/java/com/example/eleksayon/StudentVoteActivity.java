import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eleksayon.R;

public class StudentVoteActivity extends AppCompatActivity {
    private Button myButton;
    private boolean isPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.vote_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPressed = !isPressed;
                updateButtonAppearance();
            }
        });
    }

    private void updateButtonAppearance() {
        Drawable background = getResources().getDrawable(isPressed ? R.drawable.check_mark_background : R.drawable.button_background);
        myButton.setBackground(background);
    }
}
