# 🗄️ Liquibase Database Migration Guide

Welcome to the database migration guide! We use **Liquibase** to track, manage, and apply database schema changes. This ensures that our database evolves safely alongside our code, and all developers share the exact same database structure.

If you are new to Liquibase, **please read this document carefully before making any database changes.**

---

## 🏗️ 1. Directory Structure

All Liquibase configurations and migration scripts are located in `src/main/resources/db/changelog/`. We organize our files by version to keep things clean.

```text
src/main/resources/db/changelog/
├── db.changelog-master.yaml         # The root file. Do not write changes here.
├── v1.0.0/                          # Scripts for version 1.0.0
│   ├── 20260330-01-create-users.yaml
│   └── 20260330-tag-v1.0.0.yaml     # Tags the end of v1.0.0
└── v1.1.0/                          # Scripts for version 1.1.0 (Active)
    ├── 20260330-02-create-users.yaml
    └── 20260330-tag-v1.1.0.yaml     # Tags the end of v1.1.0
```

---

## 📜 2. The Golden Rules (Strictly Enforced)

Liquibase identifies a migration script (called a `changeSet`) using a composite key: **[ID] + [Author] + [File Path]**.

1. **NEVER modify an executed changeSet:** Once a script has been run against a database and merged into the main branch, it is **immutable**. Changing it will alter its checksum and crash the application on startup.
2. **Always write a Rollback:** If you create a destructive change (e.g., `dropTable`, `dropColumn`, or raw `sql`), you MUST provide a valid `<rollback>` block.
3. **Never duplicate IDs:** Use the Naming Conventions below to ensure your changeSet ID is unique.
4. **Use PreConditions:** Always check if an object exists before creating or dropping it to prevent crashes across different environments.

---

## 🏷️ 3. Naming Conventions

To avoid Git conflicts and duplicate executions, stick to the following naming standards:

* **File Name:** `[YYYYMMDD]-[sequence]-[brief-description].yaml`
    * *Example:* `20260330-01-add-status-column.yaml` (01 is the first script of the day)
* **ChangeSet ID:** Must exactly match the file name (without the `.yaml` extension).
    * *Example:* `id: 20260330-01-add-status-column`
* **Author:** Use your actual username/name. Do not use generic names like `dev` or `admin`.
    * *Example:* `author: osamuharu`
---

## 🛠️ 4. Standard Workflow for Developers

Whenever you need to change the database (add a table, insert data, modify a column), follow these steps:

1. **Create a new `.yaml` file** in the current active version folder (e.g., `v1.1.0/`).
2. **Write your script** using the standard template (see Section 5).
3. **Include your file** in `db.changelog-master.yaml` (if we are not using `includeAll`). **Note:** Place your `include` statement at the very bottom of the file to ensure it runs last.
4. **Run the application locally.** Spring Boot will automatically detect the new file and apply the changes to your local database.
5. **Commit and push** your changes. If you encounter a Git conflict in the `master.yaml` file, resolve it by ensuring the most recent scripts are at the bottom.

---

## 📝 5. The Perfect ChangeSet Template

Copy and paste this template whenever you create a new file.

```yaml
databaseChangeLog:
  - changeSet:
      id: 20260330-01-add-email-column
      author: your_name_here
      
      # 1. PRECONDITIONS: Prevent errors if the column already exists
      preConditions:
        - onFail: MARK_RAN # If the precondition fails, mark this changeSet as executed to avoid future errors
        - not:
            - columnExists:
                tableName: users
                columnName: email
                
      # 2. CHANGES: The actual database modification
      changes:
        - addColumn:
            tableName: users
            columns:
              - column:
                  name: email
                  type: VARCHAR(255)
                  constraints:
                    nullable: true
                    unique: true
                    
      # 3. ROLLBACK: How to undo this specific change
      rollback:
        - dropColumn:
            tableName: users
            columnName: email
```
*(Note: If you use `createTable`, Liquibase knows how to drop it automatically, so a manual `rollback` block is not strictly required. However, it is always required for raw SQL or destructive actions).*

---

## 🚑 6. Troubleshooting Common Errors

| Error Message                                             | Cause                                                                                               | How to Fix                                                                                                              |
|:----------------------------------------------------------|:----------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------|
| **`ValidationFailedException`** <br>*(Checksum mismatch)* | You modified a `.yaml` file AFTER it was already run against the database.                          | **Do not modify old files.** Revert the file to its original state. Create a *new* changeSet to apply your corrections. |
| **`LockException`** <br>*(Could not acquire lock)*        | The app crashed or was forcefully stopped while Liquibase was running, leaving the database locked. | Connect to your database using a SQL client and run: <br>`UPDATE DATABASECHANGELOGLOCK SET LOCKED = false;`             |
| **`Table/Column already exists`**                         | Two developers tried to create the same table, or you ran a script without a `preCondition`.        | Add a `preCondition` to your script. Coordinate with your team to avoid duplicate work.                                 |

---

### Need Help?
If you are unsure how to write a specific Liquibase tag, refer to the [Official Liquibase Documentation](https://docs.liquibase.com/change-types/home.html).