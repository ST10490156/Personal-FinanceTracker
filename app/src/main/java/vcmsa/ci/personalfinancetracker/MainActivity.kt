package vcmsa.ci.personalfinancetracker

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity





class MainActivity : AppCompatActivity() {
            private lateinit var incomeInput: EditText
            private lateinit var expensesInput: EditText
            private lateinit var calculateButton: Button
            private lateinit var balanceText: TextView
            private lateinit var feedbackText: TextView
            private lateinit var categoryFeedback: TextView

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                incomeInput = findViewById(R.id.incomeInput)
                expensesInput = findViewById(R.id.expensesInput)
                calculateButton = findViewById(R.id.calculateButton)
                balanceText = findViewById(R.id.balanceText)
                feedbackText = findViewById(R.id.feedbackText)
                categoryFeedback = findViewById(R.id.categoryFeedback)

                calculateButton.setOnClickListener {calculateFinance()}
            }


            @SuppressLint("SetTextI18n")
            private fun calculateFinance() {
                    val income = incomeInput.text.toString().toDoubleOrNull()
                    val expenses = expensesInput.text.toString().toDoubleOrNull()

                if (income == null || expenses == null) {
                    Toast.makeText(this, "Please enter valid numbers for income and expenses.", Toast.LENGTH_SHORT).show()
                    return
                }

                val balance = income - expenses
                balanceText.text = "Balance: $${"%.2f".format(balance)}"

                if (balance > 0) {
                    feedbackText.text = "You are saving money!"
                    feedbackText.setTextColor(Color.GREEN)
                } else if (balance < 0) {
                    feedbackText.text = "You are overspending!"
                    feedbackText.setTextColor(Color.RED)
                } else {
                    feedbackText.text = "You are breaking even."
                    feedbackText.setTextColor(Color.BLACK)
                }

                // Expense feedback based on the percentage of income spent
                val expensePercentage = (expenses / income) * 100
                when {
                    expensePercentage > 50 -> {
                        categoryFeedback.text = "High expenses: You're spending more than 50% of your income!"
                        categoryFeedback.setTextColor(Color.RED)
                    }
                    expensePercentage < 20 -> {
                        categoryFeedback.text = "Low expenses: You're keeping your spending low."
                        categoryFeedback.setTextColor(Color.GREEN)
                    }
                    else -> {
                        categoryFeedback.text = "Balanced spending: You're maintaining a reasonable balance."
                        categoryFeedback.setTextColor(Color.YELLOW)
            }
        }

    }
}