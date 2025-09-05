# 🎮 Videogame Ranking System

A simple Java program I originally wrote in 2022 to create a **custom videogame ranking system**.  
The idea was to compare, one by one, all the games me and my brother have played together, and slowly build a shared **top list of favorite games**.

---

## 🚀 How it works

1. **Input list of games**:  
   - All games are stored in `giochi.txt` using the format:  
     ```
     GameName & Console
     ```
   - Example:  
     ```
     Super Mario Odyssey & Switch
     The Last of Us & PS4
     ```

2. **Ranking process**:  
   - Games are compared in pairs (like a knockout tournament).  
   - If a pair has already been decided in the past, the program remembers it.  
   - Otherwise, the program asks in the terminal:  
     ```
     Super Mario [Switch] (1) o Zelda [Switch] (2) ?
     ```
   - The chosen game goes back into the queue, until only one remains.

3. **Winners file**:  
   - Each round’s winner is stored in `vincitori.txt`.  
   - Over time, the file grows into a complete ranking of all games.

---

## 📂 Project structure

```
.
├── ClassificaVideogiochi.java   # Main program
├── Videogioco.java              # Videogame class
├── Queue.java                   # Queue implementation
├── SceltaAccettabile.java       # Input validation
├── giochi.txt                   # Input list of games
└── vincitori.txt                # Ranking results (generated)
```

---

## 🛠️ Requirements

- Java 8 or higher  
- A terminal to run the program  

---

## ▶️ Usage

Compile and run from terminal:

```bash
javac *.java
java ClassificaVideogiochi
```

Then follow the on-screen prompts to compare and rank games.

---

## 📖 Example

```
Super Mario Odyssey [Switch] (1) o Zelda BOTW [Switch] (2) ?
> 2
SCELTA: [Zelda BOTW [Switch]]
```

The results will be appended to `vincitori.txt`.

---

## ✨ Future improvements

- Add a GUI (Swing / JavaFX) for easier use  
- Export results as JSON/CSV  
- Randomize matchups for more variety  
- Allow multiple players to vote together  

---

👉 This project was mainly a **fun experiment** between me and my brother to turn our gaming sessions into a ranking challenge.
