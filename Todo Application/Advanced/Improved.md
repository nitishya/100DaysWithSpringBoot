This is improved version of the task manager


Why Use DTOs and MapStruct?
DTOs decouple your internal model (Task) from what is exposed externally (API clients).
MapStruct simplifies and automates mapping between models and DTOs with minimal boilerplate.

![image](https://github.com/user-attachments/assets/a6158769-8659-44de-a5b4-2f673547b394)



## ✅ Validation and Exception Handling

This project includes validation for incoming API requests and centralized exception handling to ensure consistent error responses.

---

### 🛡️ Input Validation

We use JSR-380 Bean Validation (Jakarta Validation) on the DTO layer to ensure only valid data reaches the service layer.

#### 🔧 How It's Done

- Validation annotations are added to fields in `TaskDTO.java`:
  - `@NotBlank` — for non-empty strings
  - `@Size` — to restrict string length
  - `@NotNull` — for required non-string fields

- `@Valid` is used in controller method parameters to trigger validation automatically.

#### ✨ Example: `TaskDTO.java`

```java
@NotBlank(message = "Title is required")
private String title;

@Size(max = 255, message = "Description can't be longer than 255 characters")
private String description;

@NotNull(message = "Completed status must be specified")
private Boolean completed;
.

```

![image](https://github.com/user-attachments/assets/69ff3734-b900-46bc-aad8-fc2518286744)

