# 🎓 Spring Boot + Thymeleaf CRUD Demo

Ushbu loyiha **Java Spring Boot** va **Thymeleaf** yordamida yaratilgan oddiy CRUD (Create, Read, Update, Delete) veb-ilovasi. Talabalar ma'lumotlarini boshqarish uchun mo'ljallangan.

---

## 📸 Ko'rinish

**Talabalar ro'yxati (List)**

![List page](https://github.com/user-attachments/assets/94f9e48e-5fff-4181-982a-46888122425f)

**Yangi talaba qo'shish / tahrirlash formasi (Form)**

![Form page](https://github.com/user-attachments/assets/147633a9-3e79-4565-89df-2f7ec29087ad)

---

## ▶️ Tezkor ishga tushirish

```bash
mvn spring-boot:run
```
Brauzerda oching: **http://localhost:8080/students**

---

## 🛠 Texnologiyalar

| Texnologiya | Versiya | Maqsad |
|---|---|---|
| Java | 17 | Asosiy dasturlash tili |
| Spring Boot | 3.2.3 | Web framework |
| Spring Data JPA | — | Ma'lumotlar bazasi bilan ishlash |
| Thymeleaf | — | HTML shablonlar (server-side rendering) |
| H2 Database | — | In-memory ma'lumotlar bazasi |
| Maven | — | Loyiha va dependency boshqarish |

---

## 📁 Loyiha tuzilmasi

```
src/
└── main/
    ├── java/com/example/crud/
    │   ├── CrudApplication.java          ← Asosiy kirish nuqtasi
    │   ├── model/
    │   │   └── Student.java              ← Entity (JPA model)
    │   ├── repository/
    │   │   └── StudentRepository.java    ← Ma'lumotlar bazasi so'rovlari
    │   ├── service/
    │   │   └── StudentService.java       ← Biznes mantiq
    │   └── controller/
    │       └── StudentController.java    ← HTTP so'rovlarni qayta ishlash
    └── resources/
        ├── templates/students/
        │   ├── list.html                 ← Ro'yxat sahifasi
        │   └── form.html                 ← Qo'shish/tahrirlash formasi
        └── application.properties        ← Konfiguratsiya
```

---

## ⚙️ CRUD Operatsiyalari

| Amal | HTTP Metod | URL | Tavsif |
|---|---|---|---|
| **Read** | GET | `/students` | Barcha talabalarni ko'rsatish |
| **Create** | GET | `/students/new` | Yangi talaba formasi |
| **Create** | POST | `/students` | Yangi talabani saqlash |
| **Update** | GET | `/students/{id}/edit` | Tahrirlash formasi |
| **Update** | POST | `/students/{id}` | O'zgarishlarni saqlash |
| **Delete** | POST | `/students/{id}/delete` | Talabani o'chirish |

---

## 🚀 Ishga tushirish

### Talablar

- Java 17 yoki undan yuqori
- Maven 3.6+

### 1. Repozitoriyni klonlash

```bash
git clone https://github.com/Shi200301/testAgent.git
cd testAgent
```

### 2. Loyihani build qilish

```bash
mvn clean package -DskipTests
```

### 3. Ishga tushirish

```bash
java -jar target/crud-demo-0.0.1-SNAPSHOT.jar
```

Yoki Maven orqali to'g'ridan-to'g'ri:

```bash
mvn spring-boot:run
```

### 4. Brauzerda ochish

```
http://localhost:8080/students
```

> **H2 Console** (ma'lumotlar bazasini ko'rish uchun):
> ```
> http://localhost:8080/h2-console
> JDBC URL: jdbc:h2:mem:cruddb
> Username: sa
> Password: (bo'sh)
> ```

---

## 🧪 Testlarni ishga tushirish

```bash
mvn test
```

---

## 📝 Kod izohlar

### `Student.java` — Entity

```java
@Entity
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank  // Validation: bo'sh bo'lmasligi kerak
    private String name;

    @NotBlank
    private String email;

    private String course;
    // getter/setter ...
}
```

### `StudentRepository.java` — Repository

```java
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository barcha asosiy CRUD metodlarini taqdim etadi:
    // findAll(), findById(), save(), deleteById() va hokazo
}
```

### `StudentService.java` — Service

Biznes mantiqni controller va repository'dan ajratadi.

### `StudentController.java` — Controller

```java
@Controller
@RequestMapping("/students")
public class StudentController {
    @GetMapping           // GET /students → ro'yxat
    @GetMapping("/new")   // GET /students/new → forma
    @PostMapping          // POST /students → saqlash
    @GetMapping("/{id}/edit")   // tahrirlash formasi
    @PostMapping("/{id}")        // yangilash
    @PostMapping("/{id}/delete") // o'chirish
}
```

### Thymeleaf shablonlari

`list.html` — `th:each` bilan ro'yxatni iteratsiya qiladi:
```html
<tr th:each="student, stat : ${students}">
    <td th:text="${student.name}">Ism</td>
    ...
</tr>
```

`form.html` — `th:object` va `th:field` bilan form binding:
```html
<form th:object="${student}" method="post">
    <input th:field="*{name}" />
    <span th:errors="*{name}" class="error"></span>
</form>
```
