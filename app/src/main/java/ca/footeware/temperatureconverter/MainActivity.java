package ca.footeware.temperatureconverter;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText celsiusEditText;
    private EditText fahrenheitEditText;
    private EditText kelvinEditText;
    private TextWatcher celsiusTextWatcher;
    private TextWatcher fahrenheitTextWatcher;
    private TextWatcher kelvinTextWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCelsiusEditText().addTextChangedListener(getCelsiusTextWatcher());
        getFahrenheitEditText().addTextChangedListener(getFahrenheitTextWatcher());
        getKelvinEditText().addTextChangedListener(getKelvinTextWatcher());
    }

    private EditText getCelsiusEditText() {
        if (this.celsiusEditText == null) {
            this.celsiusEditText = (EditText) findViewById(R.id.editTextNumberDecimal_celsius);
        }
        return this.celsiusEditText;
    }

    private EditText getFahrenheitEditText() {
        if (this.fahrenheitEditText == null) {
            this.fahrenheitEditText = (EditText) findViewById(R.id.editTextNumberDecimal_fahrenheit);
        }
        return this.fahrenheitEditText;
    }

    private EditText getKelvinEditText() {
        if (kelvinEditText == null) {
            kelvinEditText = (EditText) findViewById(R.id.editTextNumberDecimal_kelvin);
        }
        return kelvinEditText;
    }

    private TextWatcher getCelsiusTextWatcher() {
        if (this.celsiusTextWatcher == null) {
            this.celsiusTextWatcher = new CelsiusTextWatcher();
        }
        return this.celsiusTextWatcher;
    }

    private TextWatcher getFahrenheitTextWatcher() {
        if (this.fahrenheitTextWatcher == null) {
            this.fahrenheitTextWatcher = new FahrenheitTextWatcher();
        }
        return this.fahrenheitTextWatcher;
    }

    private TextWatcher getKelvinTextWatcher() {
        if (this.kelvinTextWatcher == null) {
            this.kelvinTextWatcher = new KelvinTextWatcher();
        }
        return this.kelvinTextWatcher;
    }

    private String getFahrenheitFromCelsius(CharSequence s) {
        return String.valueOf(Double.parseDouble(s.toString()) * (9.0 / 5.0) + 32.0);
    }

    private String getKelvinFromCelsius(CharSequence s) {
        return String.valueOf(Double.parseDouble(s.toString()) + 273.15);
    }

    private String getCelsiusFromFahrenheit(CharSequence s) {
        return String.valueOf(Double.parseDouble(s.toString()) - 32.0 * (5.0 / 9.0));
    }

    private String getKelvinFromFahrenheit(CharSequence s) {
        return String.valueOf((Double.parseDouble(s.toString()) - 32.0) * (5.0 / 9.0) + 273.15);
    }

    private String getCelsiusFromKelvin(CharSequence s) {
        return String.valueOf(Double.parseDouble(s.toString()) - 273.15);
    }

    private String getFahrenheitFromKelvin(CharSequence s) {
        return String.valueOf((Double.parseDouble(s.toString()) - 273.15) * (9.0 / 5.0) + 32.0);
    }

    private class CelsiusTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            getFahrenheitEditText().removeTextChangedListener(getFahrenheitTextWatcher());
            getKelvinEditText().removeTextChangedListener(getKelvinTextWatcher());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0 || s.toString().equals("-") || s.toString().equals(".")) {
                getFahrenheitEditText().setText("");
                getKelvinEditText().setText("");
            } else {
                getFahrenheitEditText().setText(getFahrenheitFromCelsius(s));
                getKelvinEditText().setText(getKelvinFromCelsius(s));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            getFahrenheitEditText().addTextChangedListener(getFahrenheitTextWatcher());
            getKelvinEditText().addTextChangedListener(getKelvinTextWatcher());
        }
    }

    private class FahrenheitTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            getCelsiusEditText().removeTextChangedListener(getCelsiusTextWatcher());
            getKelvinEditText().removeTextChangedListener(getKelvinTextWatcher());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0 || s.toString().equals("-") || s.toString().equals(".")) {
                getCelsiusEditText().setText("");
                getKelvinEditText().setText("");
            } else {
                getCelsiusEditText().setText(getCelsiusFromFahrenheit(s));
                getKelvinEditText().setText(getKelvinFromFahrenheit(s));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            getCelsiusEditText().addTextChangedListener(getCelsiusTextWatcher());
            getKelvinEditText().addTextChangedListener(getKelvinTextWatcher());
        }
    }

    private class KelvinTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            getCelsiusEditText().removeTextChangedListener(getCelsiusTextWatcher());
            getFahrenheitEditText().removeTextChangedListener(getFahrenheitTextWatcher());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0 || s.toString().equals("-") || s.toString().equals(".")) {
                getCelsiusEditText().setText("");
                getFahrenheitEditText().setText("");
            } else {
                getCelsiusEditText().setText(getCelsiusFromKelvin(s));
                getFahrenheitEditText().setText(getFahrenheitFromKelvin(s));
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            getCelsiusEditText().addTextChangedListener(getCelsiusTextWatcher());
            getFahrenheitEditText().addTextChangedListener(getFahrenheitTextWatcher());
        }
    }

}