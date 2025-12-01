🎯 About the Application
Restaurant Management System is a console-based application designed to facilitate restaurant menu management, customer order recording, automatic discounted payment calculation, and data storage to files.

🎓 Learning Outcomes
This application successfully implements:

✅ Abstraction using Abstract Class and Sealed Class
✅ Inheritance with structured class hierarchy
✅ Encapsulation through access modifiers and getter/setter methods
✅ Polymorphism with method overriding and runtime polymorphism
✅ Exception Handling for robust error management
✅ File I/O for persistent data storage
✅ Collection Framework (ArrayList) for data management
✅ Modern Java 25 features (Pattern Matching, Switch Expressions, Text Blocks, etc.)


✨ Key Features
1. Menu Management

Add new menu items (Food, Beverages, or Discount Items)
Display complete menu list with organized formatting
Save menu to menu_restoran.txt file
Load menu from file when application starts

2. Order Recording

Record customer orders with name and timestamp
Add multiple items to a single order
Input validation with exception handling

3. Automatic Calculation

Calculate total payment accurately
Automatic discount for items in "Discount" category
Display detailed calculations in receipt

4. Receipt Generation

Professional format order receipt
Item details, prices, and discounts
Order timestamp
Total payment

5. File Operations

Save menu to text file
Load menu from file
Export order receipt to file with unique name (timestamp)

🏗️ OOP Concepts Implementation
1. Abstraction
javapublic abstract sealed class MenuItem permits Makanan, Minuman, Diskon {
    // Abstract methods that must be implemented
    public abstract void tampilMenu();
    public abstract String toFileString();
}
Implementation:

Abstract class MenuItem as blueprint
Abstract methods that must be implemented by subclasses
Sealed class to restrict inheritance

2. Inheritance
           MenuItem (Abstract)
                 |
     ┌───────────┼───────────┐
     |           |           |
  Makanan    Minuman     Diskon
  (Food)   (Beverage)  (Discount)
Implementation:

Makanan, Minuman, and Diskon extend MenuItem
Inherit attributes: name, price, category
Add specific attributes in each subclass

3. Encapsulation
javaprivate String nama;      // Private attribute
private double harga;

public String nama() {    // Public getter
    return nama;
}

public void setNama(String nama) {  // Public setter
    this.nama = nama;
}
Implementation:

All attributes use private modifier
Data access through public getters and setters
Data protection and validation

4. Polymorphism
java// Runtime Polymorphism
for (MenuItem item : daftarMenu) {
    item.tampilMenu();  // Calls different method depending on object type
}
Implementation:

Method overriding in each subclass
Runtime polymorphism in collections
Polymorphic behavior for discount price calculation


📁 Project Structure
restaurant-management-system/
│
├── src/
│   ├── MenuItem.java          # Abstract parent class (sealed)
│   ├── Makanan.java           # Subclass for food items
│   ├── Minuman.java           # Subclass for beverage items
│   ├── Diskon.java            # Subclass for discount items
│   ├── Menu.java              # Menu list manager class
│   ├── Pesanan.java           # Customer order manager class
│   └── RestoranApp.java       # Main class (entry point)
│
├── data/
│   ├── menu_restoran.txt      # Menu storage file (auto-generated)
│   └── struk_*.txt            # Order receipt files (auto-generated)
│
├── docs/
│   └── Detailed_Explanation.md  # Detailed documentation for presentation
│
├── README.md                  # This file
└── LICENSE                    # MIT License

🚀 Installation
Prerequisites

Java Development Kit (JDK) 17 or higher (recommended: JDK 25)
Text editor or IDE (VS Code, IntelliJ IDEA, Eclipse, NetBeans, etc.)
Terminal/Command Prompt

Installation Steps

Clone the repository

bash   git clone https://github.com/username/restaurant-management-system.git
   cd restaurant-management-system

Compile all Java files

bash   javac *.java
Or if in src folder:
bash   cd src
   javac *.java

Run the application

bash   java RestoranApp

📖 Usage Guide
Main Menu
After the application runs, you will see the main menu with 8 options:
======================================================================
                           MAIN MENU
======================================================================
  1. Add New Item to Menu
  2. Display Restaurant Menu
  3. Take Customer Order
  4. Calculate Total Order Cost
  5. Display Order Receipt
  6. Save Menu to File
  7. Save Order Receipt to File
  8. Exit
======================================================================
1️⃣ Add New Menu Item
Select menu [1-8]: 1

Item name: Beef Rendang
Price: Rp 30000

Select item type:
1. Food
2. Beverage
3. Discount Item
Choice: 1

Food type (Nusantara/Western/Chinese/etc): Nusantara

✓ Item successfully added to menu!
2️⃣ Display Menu
Select menu [1-8]: 2

======================================================================================
                               RESTAURANT MENU LIST
======================================================================================
1. Nasi Goreng            | Rp 15000     | Type: Nusantara      | Category: Food
2. Sate Ayam              | Rp 20000     | Type: Nusantara      | Category: Food
...
11. Bakso Spesial         | Rp 12000     | Discount: 10% | Discounted Price: Rp 10800
======================================================================================
3️⃣ Take Order
Select menu [1-8]: 3

Enter customer name: Budi Santoso
✓ Order for Budi Santoso has started.

Select menu number to order (0 to finish): 1
✓ Nasi Goreng added to order!

Select menu number to order (0 to finish): 11
✓ Bakso Spesial added to order!

Select menu number to order (0 to finish): 0
✓ Order successfully recorded!
4️⃣ Calculate Total
Select menu [1-8]: 4

======================================================================
TOTAL ORDER COST: Rp 25800
======================================================================
5️⃣ Display Receipt
Select menu [1-8]: 5

======================================================================
                         ORDER RECEIPT
======================================================================
Customer Name  : Budi Santoso
Order Time     : 01-12-2024 14:30:45
----------------------------------------------------------------------
1. Nasi Goreng                  Rp      15000
2. Bakso Spesial                Rp      12000
   (10% Discount)               - Rp       1200
   Price After Discount         Rp      10800
----------------------------------------------------------------------
TOTAL PAYMENT                            Rp      25800
======================================================================
            Thank you for your visit!
======================================================================
6️⃣ Save Menu to File
Select menu [1-8]: 6

✓ Menu successfully saved to file: menu_restoran.txt
File format menu_restoran.txt:
MAKANAN|Nasi Goreng|15000|Nusantara
MAKANAN|Sate Ayam|20000|Nusantara
MINUMAN|Es Teh Manis|5000|Dingin
DISKON|Bakso Spesial|12000|10
7️⃣ Save Receipt to File
Select menu [1-8]: 7

✓ Receipt successfully saved to file: struk_20241201_143045.txt
8️⃣ Exit
Select menu [1-8]: 8

✓ Thank you for using the application. Goodbye!

📸 Screenshots
Main Menu
======================================================================
        WELCOME TO RESTAURANT MANAGEMENT SYSTEM
======================================================================
✓ Menu successfully loaded from file: menu_restoran.txt

======================================================================
                           MAIN MENU
======================================================================
  1. Add New Item to Menu
  2. Display Restaurant Menu
  ...
Menu List with Polymorphism
1. Nasi Goreng            | Rp 15000     | Type: Nusantara      | Category: Food
2. Es Teh Manis           | Rp 5000      | Type: Cold           | Category: Beverage
3. Bakso Spesial          | Rp 12000     | Discount: 10% | Discounted Price: Rp 10800
Note: Each item type (Food/Beverage/Discount) displays with different format - this is Polymorphism in action!
Order Receipt with Discount Details
======================================================================
                         ORDER RECEIPT
======================================================================
Customer Name  : Budi Santoso
Order Time     : 01-12-2024 14:30:45
----------------------------------------------------------------------
1. Nasi Goreng                  Rp      15000
2. Bakso Spesial                Rp      12000
   (10% Discount)               - Rp       1200
   Price After Discount         Rp      10800
3. Es Teh Manis                 Rp       5000
----------------------------------------------------------------------
TOTAL PAYMENT                            Rp      30800
======================================================================

🛠️ Technologies Used
Core Technologies

Java 25 - Programming Language
Java NIO (Files API) - Modern File I/O Operations
Java Collections Framework - ArrayList for data management
Java Time API - LocalDateTime for timestamps

Java 25 Modern Features

✅ Sealed Classes - Type-safe inheritance control
✅ Pattern Matching for instanceof - Cleaner type checking
✅ Switch Expressions - Modern switch syntax
✅ Text Blocks - Multi-line string literals
✅ Local Variable Type Inference (var) - Type inference
✅ Stream API - Functional programming
✅ Records (Optional) - Immutable data carriers

Design Patterns

Template Method Pattern - Abstract class with abstract methods
Factory Pattern - Creation of MenuItem objects
Composition - Menu has-a ArrayList of MenuItem


💻 System Requirements
Minimum Requirements

OS: Windows 7/8/10/11, macOS 10.12+, Linux (Ubuntu 18.04+)
Java: JDK 17 or higher
RAM: 256 MB
Storage: 50 MB free space

Recommended Requirements

OS: Windows 10/11, macOS 12+, Linux (Ubuntu 22.04+)
Java: JDK 25
RAM: 512 MB
Storage: 100 MB free space
IDE: IntelliJ IDEA, VS Code, or Eclipse


📚 Documentation
Class Diagram
┌─────────────────────────────────────┐
│      MenuItem (Abstract Sealed)     │
├─────────────────────────────────────┤
│ - nama: String                      │
│ - harga: double                     │
│ - kategori: String                  │
├─────────────────────────────────────┤
│ + nama(): String                    │
│ + harga(): double                   │
│ + setNama(String): void             │
│ + setHarga(double): void            │
│ + tampilMenu(): void [abstract]     │
│ + toFileString(): String [abstract] │
│ + hargaAkhir(): double              │
└──────────────┬──────────────────────┘
               │
     ┌─────────┼─────────┐
     │         │         │
┌────▼─────┐┌─▼────────┐┌▼────────────┐
│ Makanan  ││ Minuman  ││   Diskon    │
│ (Food)   ││(Beverage)││ (Discount)  │
├──────────┤├──────────┤├─────────────┤
│- jenis   ││- jenis   ││- persenDiskon
│  Makanan ││  Minuman ││             │
└──────────┘└──────────┘└─────────────┘

┌─────────────────┐         ┌──────────────────┐
│      Menu       │         │     Pesanan      │
│                 │         │     (Order)      │
├─────────────────┤         ├──────────────────┤
│- daftarMenu:    │         │- itemPesanan:    │
│  ArrayList      │         │  ArrayList       │
├─────────────────┤         │- namaPelanggan   │
│+ tambahItem()   │         │- waktuPesan      │
│+ getItem()      │         ├──────────────────┤
│+ tampilkanMenu()│         │+ tambahItem()    │
│+ simpanKeFile() │         │+ hitungTotal()   │
│+ muatDariFile() │         │+ tampilkanStruk()│
└─────────────────┘         │+ simpanKeFile()  │
                            └──────────────────┘
Detailed Documentation
For detailed explanations about OOP implementation and Java 25 features, please read:

Detailed Explanation (Indonesian)
15-Minute Presentation Script


🤝 Contributing
Contributions are welcome! If you'd like to contribute:

Fork this repository
Create a new feature branch (git checkout -b amazing-feature)
Commit your changes (git commit -m 'Add amazing feature')
Push to the branch (git push origin amazing-feature)
Create a Pull Request

Contribution Guidelines

Follow existing code style
Write comments for complex code
Update README if adding new features
Test all features before submitting PR


📄 License
This project is licensed under the MIT License - see the LICENSE file for details.
MIT License

Copyright (c) 2025 Vinto Rizalfi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...