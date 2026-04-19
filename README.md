# 🏙️ City Simulation Game | لعبة محاكاة المدن

---

## 📌 Overview | نظرة عامة

**EN:**
This project is a console-based Java application that simulates cities, districts, neighborhoods, and people.
The population evolves dynamically over multiple rounds based on defined rules.

**AR:**
هذا المشروع عبارة عن برنامج محاكاة باستخدام Java يعمل على الكونسول، يقوم بمحاكاة المدن والمناطق والأحياء والسكان، حيث يتغير عدد السكان مع مرور الجولات وفق نظام محدد.

---

## 🎯 Core Features | المميزات الأساسية

**EN:**

* Dynamic city generation from user input
* Hierarchical structure (City → District → Neighborhood → Person)
* Population growth system
* Automatic city splitting
* People aging system
* Realistic random data using Java Faker
* Structured console output
* City details view at the end

**AR:**

* إنشاء المدن ديناميكيًا من إدخال المستخدم
* هيكل هرمي (مدينة → منطقة → حي → شخص)
* نظام نمو السكان
* انقسام المدن تلقائيًا
* نظام زيادة العمر
* استخدام Faker لتوليد بيانات واقعية
* عرض منظم في الكونسول
* إمكانية عرض تفاصيل مدينة محددة

---

## 🧠 Simulation Logic | منطق المحاكاة

### 🔢 Input | الإدخال

```text
17 35 98 65
```

**EN:**

* Tens digit → number of districts
* Ones digit → number of neighborhoods

**AR:**

* العشرات → عدد المناطق
* الآحاد → عدد الأحياء

---

### 📈 Population Growth | نمو السكان

**EN:**
Growth factor is calculated using the last two digits of the population.

**AR:**
يتم حساب النمو من خلال جمع الرقمين الأخيرين من عدد السكان.

---

### ✂️ City Splitting | انقسام المدن

**EN:**
If a city reaches a 4-digit population:

* It splits into two cities
* Half of its districts move to a new city

**AR:**
إذا وصل عدد سكان المدينة إلى 4 أرقام:

* تنقسم إلى مدينتين
* يتم نقل نصف المناطق إلى مدينة جديدة

---

## 🧱 Project Structure | بنية المشروع

```text
src/
 └── odev1/
     ├── Main.java
     ├── Oyun.java
     ├── Sehir.java
     ├── Ilce.java
     ├── Mahalle.java
     ├── Kisi.java
     ├── FakerServisi.java
     ├── NufusHelper.java
     ├── EkranYazdirici.java
     └── IdUretici.java
```

---

## ⚙️ Technologies | التقنيات

* Java
* Eclipse IDE
* Java Faker

---

## ▶️ Running the Project | تشغيل المشروع

### 🔹 Eclipse

Run:

```text
Main.java
```

---

### 🔹 Terminal

```bash
cd src
javac -cp ".;../lib/*" odev1/*.java
java -cp ".;../lib/*" odev1.Main
```

---

## 🖥️ Sample Output | مثال مخرجات

```text
[21]-[36]-[99]-[66]-[48]
[32]-[36]-[15]-[24]-[66]

--- Tur 1 bitti ---
[63]-[324]-[990]-[792]-[576]
```

---

## 🔍 City Details | عرض التفاصيل

**EN:**
At the end, the user selects a city by row and column to display full details.

**AR:**
في نهاية البرنامج يمكن اختيار مدينة باستخدام الصف والعمود لعرض تفاصيلها بالكامل.

---

## 👨‍💻 Author | المطور

BELAL ALHAMID 
Computer Engineering

---
