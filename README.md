# TaskCLI

TaskCLI is a lightweight Java command-line application that lets you create, update, delete and list your tasks. All data is stored locally in a `tasks.json` file for easy persistence.

---

## 🚀 Features

- Create new tasks with a single command  
- Update a task’s title or status (`TODO`, `IN_PROGRESS`, `DONE`)  
- Remove tasks by their ID  
- List all tasks or filter them by status  
- Simple JSON-based storage—no database required  

---

## 🔧 Prerequisites

- Java 8 or higher installed on your system  
- (Optional) Maven, if you prefer to build from source  

---

## 📝 Commands
|---------------------------------|------------------------------------------|
| Command                         | Description                              |
|---------------------------------|------------------------------------------|
| `help`                          | Display the list of available commands   |
| `add <task description>`        | Create a new task                        |
| `update <id> <new description>` | Change the title of an existing task     |
| `remove <id>`                   | Delete a task by its ID                  |
| `mark-in-progress <id>`         | Set a task’s status to **IN_PROGRESS**   |
| `mark-done <id>`                | Set a task’s status to **DONE**          |
| `list [status]`                 | Show all tasks or only those by status   |
| `exit`                          | Exit the application                     |
|---------------------------------|------------------------------------------|
---

## 🔗 Project Info

This project is part of the [Backend Developer Roadmap](https://roadmap.sh/projects/task-tracker).
