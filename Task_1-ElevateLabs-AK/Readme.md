# Java Console Calculator

A simple console-based calculator program written in **Java**.  
It supports basic arithmetic operations such as **addition, subtraction, multiplication, division, and modulo** with an option to continue or exit after each calculation.  

---

## 🚀 Features
- Add two numbers  
- Subtract two numbers  
- Multiply two numbers  
- Divide two numbers (with zero-division check)  
- Modulo operation  
- Option to continue or exit after each calculation  

---

## 📂 Project Structure
```
Calc.java   // Main program file
```

---

## 🛠️ Requirements
- **Java JDK 8 or later**  
- A terminal/command prompt to run the program  

---

## ▶️ How to Run
1. Save the file as `Calc.java`.
2. Open terminal and navigate to the file location.
3. Compile the program:
   ```bash
   javac Calc.java
   ```
4. Run the program:
   ```bash
   java Calc
   ```

---

## 📖 Usage
1. When the program starts, you’ll see a menu:

   ```
   Operations 

    1 - Add 
    2 - Subtract 
    3 - Multiply 
    4 - Divide 
    5 - Modulo 
    6 - Exit 
   select operation -
   ```

2. Select an operation (1–6).  
3. Enter two numbers when prompted.  
4. The result will be displayed.  
5. You will be asked:
   ```
   want to continue (1)
   exit (0)
   ```
   - Enter `1` → Perform another operation.  
   - Enter `0` → Exit the program.  

---

## ⚠️ Notes
- Division by **zero** is not allowed (the program will warn you).  
- Only integer values (long type) are supported.  
- Input outside the valid menu range will show `"Invalid Operation Selection"`.  

---

## 📌 Example
```
Operations 

 1 - Add 
 2 - Subtract 
 3 - Multiply 
 4 - Divide 
 5 - Modulo 
 6 - Exit 
select operation - 1

Enter Two values 	
a: 
5
b: 
3
 = 8

want to continue (1)
exit (0) 
```

---

## 🔮 Future Enhancements
- Support for **floating-point numbers (double/float)**  
- Advanced operations (power, square root, logarithm, trigonometric functions)  
- Input validation with error handling for non-numeric inputs  
- History of previous calculations  
- GUI-based version using **Java Swing** or **JavaFX**  
- Packaging as an executable `.jar` file for easy distribution  

---

## 👨‍💻 Author
- Developed by AK  


