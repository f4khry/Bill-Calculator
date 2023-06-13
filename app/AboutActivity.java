import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize the views
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);

        // Set the text for the views
        titleTextView.setText("About Page");
        descriptionTextView.setText("Add your about page details here.");
    }
}



