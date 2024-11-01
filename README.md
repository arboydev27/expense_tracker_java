# expense_tracker_java
A Java-based expense tracking application that helps users monitor their finances across various categories over a period of months. This tool stores, retrieves, and analyzes expenses, enabling better budgeting and financial management.

Features:
• Expense Categories: Track expenses across categories like Utilities, Entertainment, Rent, and Food. Additional categories can be added as needed.
• Monthly Tracking: Record and view expenses for four months, allowing insights into spending trends.
• Data Analysis:
    - Monthly Totals: Calculate the total spent in any given month.
    - Category Totals: Summarize expenses by category across the entire four-month period.
    - Grand Total: Compute the total expenses across all categories and months.
    - Range Filter: Find total expenses within a specific range (e.g., $50 - $100).
• Data Persistence: Stores data in a file for easy retrieval, updates, and analysis.

Getting Started:
1. Setup: Clone the repository and import the project into your preferred IDE (e.g., Eclipse).
2. Run: Execute the main program and follow the on-screen prompts to add, view, and analyze expense data.
3. Customize: Add new categories to tailor the tracker to your financial needs.

How It Works:
The program organizes data in three arrays:
• Categories (e.g., Utilities, Rent)
• Months (e.g., Jan, Feb)
• Expenses: A two-dimensional array holding the expenses for each category and month.

Sample Operations:
• Add a new expense category.
• View total spending by category or month.
• Analyze total spending within a specific range.