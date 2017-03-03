package com.example.sashopc.delcandroidtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Quiz.db";
    public static final String  CLMN_ANSWER = "Answer";
    public static final String CLMN_IS_CORRECT = "IsCorrect";
    public static final String CLMN_QUESTION_TYPE="Type";

    public static final String FirstTABLE_NAME = "Question_table";
    public static final String SecondTABLE_NAME = "Answer_table";
    public static final String ThirdTABLE_NAME = "Student_table";
    public static final String FourthTABLE_NAME ="Grades_table";
    public static final String FifthTABLE_NAME = "QuestionAnswer_table";
    public static final String SixthTABLE_NAME = "CSharpQuestion_table";
    public static final String SeventhTABLE_NAME = "CSharpAnswer_table";
    public static final String EightTABLE_NAME = "JavaQuestion_table";
    public static final String NinethTABLE_NAME = "JavaAnswer_table";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FirstTABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Question TEXT, Type INTEGER)");
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Какво е Android ?',1)");//1
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Какво е Google Android SDK ?',1)");//2
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Какво значи акронима AAPT ?',1)");//3
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Необходими ли са емулаторите в Android Studio ?',2)");//4
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Какво е Android AVD Manager ?',1)");//5
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('За какво се използва Intent в Android ?',2)");//6
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Какво е Fragment ?',1)");//7
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Всички телефони ли потдържат най-новата версия на Android ?',2)");//8
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Какъв език се ползва за писането на Android приложения ?',1)");//9
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES('Какво означава ANR за приложение ?',1)");//10
        db.execSQL("INSERT INTO " + FirstTABLE_NAME + "(Question,Type) VALUES(' Въведете курса си',3)");//11



        db.execSQL("create table " + SixthTABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Question TEXT, Type INTEGER)");
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Какво е C# ?',1)");//1
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Колко вида коментари се използват в C# ?',1)");//2
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Избройте типовете коментари:' ,3)");//3
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Какво е namespace за C#.NET ?',1)");//4
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Какво е присъщо за C# като език ?',2)");//5
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Колко различни категории на наследяване има в C# ?',1)");//6
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Кои са основните концепции в ООП ?',1)");//7
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Може ли да се използва множествено наследяване в C# ?',2)");//8
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Колко вида кеширане в C# съшествуват ?',1)");//9
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES('Какво е капсулация в C# ?',1)");//10
        db.execSQL("INSERT INTO " + SixthTABLE_NAME + "(Question,Type) VALUES(' Въведете курса си',3)");//11

        db.execSQL("create table " +SecondTABLE_NAME + "(AnswerID INTEGER PRIMARY KEY AUTOINCREMENT,QuestionID INTEGER, Answer TEXT, IsCorrect INTEGER)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'Отворена операционна система за мобилни устройства',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'Модел телефон',0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'Не знам какво означава',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'Android SDK са инструменти който разработчиците на приложения за телефони използват',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'Тоолбар',0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'Не знам какво е това',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(3,'Android Asset Packaging Tool',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(3,'Association of Android Providing Technologists',0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(3,'Android Advanced Professional Technicians',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Да',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Без емулатор не може',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'He',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'В програмата не се намира такова нещо',0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Инструмент за създаване на емулаторни устройства',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Това е activity',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'За преминаване от едно в друго Activity ',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'За пренасане на параметри',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'За стартиране на Activity',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'То е част или порция от Activity',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'То е Activity',0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'Нямам представа за това',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Зависи колко бързо пристигат ъпдейти за даденото устройство',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Не',0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Зависи от операционната система на устройството',1)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'Java',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'JavaScript',0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'C#',0)");

        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'Application Not Responding се случва',1)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'Application Not Running се случва', 0)");
        db.execSQL("INSERT INTO " + SecondTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'Android Not Responding се случва',0)");


        db.execSQL("create table " + ThirdTABLE_NAME + "(FacultyNumber INTEGER PRIMARY KEY,FirstName TEXT, LastName TEXT)");

        db.execSQL("create table " + EightTABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Question TEXT, Type INTEGER)");
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Кое от изброените не е ключова дума в java',1)");//1
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Какъв е размерът на булевата стойност ?',1)");//2
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Колко е стойността по подразбиране за byte променлива ?',1)");//3
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Кое от изброените е вярно за супер класа ?',2)");//4
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Какво е инстанция на променливата ?',1)");//5
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Композицията представлява: ',1)");//6
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Кое е вярно за final класа ?',2)");//7
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Какво е synchronization?',1)");//8
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('При какви обстоятелства метода finalize() на обекта се изпълнява от garbage collector ?',1)");//9
        db.execSQL("INSERT INTO " + EightTABLE_NAME + "(Question,Type) VALUES('Кое от изброените е threat safe?',1)");//10
        db.execSQL("INSERT INTO " + EightTABLE_NAME+ "( Question,Type) VALUES('Въведете курса си',3)");//11


        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681001','Симеон','Балабанов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681002','Александър','Шаранков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681003','Дафинка','Герджикова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681005','Рени','Тодорова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681006','Димитрия','Гаджева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681007','Петьо','Ружин')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681008','Яна','Славева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681009','Мая','Костадинова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681010','Даница','Андреева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681011','Антонио','Кацаров')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681012','Ивайло','Стоянов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681013','Васил','Запрянов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681014','Златко','Петров')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681015','Красимир','Годуманов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681016','Яанко','Иванов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681017','Дарина','Тарева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681018','Нонка','Владимирова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681020','Станислав','Стоилов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681021','Лилия','Ихтиманска')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681022','Джанета','Станилова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681023','Пламена','Раднева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681024','Анна','Кривова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681025','Александър','Русаков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681026','Мила','Арсенова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681027','Даниел','Димитров')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681028','Асен','Танев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681029','Никола','Вълков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681030','Дело','Брънчев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681031','Павел','Богданов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681032','Николай','Инджов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681033','Яна','Минкова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681034','Николай','Пенев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681035','Михаил','Лулов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681036','Станил','Колев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681037','Милена','Стоева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681038','Зовка','Манова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681039','Велислав','Ичев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681040','Иван','Делев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681041','Сезен','Реджеп')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681042','Камелия','Василева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681044','Хюсеин','Хъйрола')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681045','Ерхан','Мустафа')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681046','Катерина','Славова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681048','Веселин','Симеонов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681050','Александър','Балджийски')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681051','Стилян','Мирчев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681052','Даниел','Динев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681053','Кристина','Декова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681054','Диана','Морева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681055','Стела','Караманкова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681056','Мартин','Андреев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681057','Пламен','Радичков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681058','Иван','Червенков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681059','Румен','Чонков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681060','Дияна','Димитрова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681061','Катрин','Спасова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681063','Фани','Карамфилова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681064','Георги','Николов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681065','Венцислав','Стоянов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681066','Иван','Иванов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681067','Евелина','Александрова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681069','Симона','Иванова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681070','Мария','Видева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681071','Александър','Травлев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681073','Петър','Йочев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681074','Карамфил','Ботев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681075','Клаудия','Петкова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681076','Радеон','Доксанлиев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681078','Яни','Пиргов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681079','Христиан','Кахчиев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681080','Теодор','Стаменов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681081','Александър','Инджов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681082','Ива','Тсонева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681083','Иван','Арамазов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681084','Николай','Иванов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681085','Петър','Земянски')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681086','Димитър','Лъсков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681087','Диана','Илиева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681089','Николай','Тънковски')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681090','Мирослав','Щерев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681091','Милен','Пенев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681093','Христо','Енчев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681094','Марко','Марков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681095','Надежда','Шилева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681096','Йордан','Ружинов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681097','Иван','Абаджиев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681098','Христо','Ченгелов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681099','Гергана','Ангелова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681100','Десислава','Ангелова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681101','Петя','Тончева')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681102','Васил','Минчев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681103','Христо','Семков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681104','Стефан','Николов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681105','Петя','Бимбалова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681106','Иван','Мадин')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681107','Недко','Налбантов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681108','Цоко','Бурмов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681110','Костадин','Караиванов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681111','Станислава','Арамазова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681113','Димитър','Пенев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681115','Анатолий','Кехайов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681117','Стефан','Георгиев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681118','Кристина','Стефанова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681120','Иван','Димитров')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681121','Даниел','Янков')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681122','Йосив','Инджов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681123','Елена','Димитрова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681124','Никола','Рангелов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681126','Иван','Господинов')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681114','Михаела','Асенова')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681049','Григорий','Василев')");
        db.execSQL("INSERT INTO " + ThirdTABLE_NAME + "(FacultyNumber,FirstName,LastName) VALUES('1301681119','Гергана','Нанкинова')");

        db.execSQL("create table " + FourthTABLE_NAME + "(GradeID INTEGER PRIMARY KEY AUTOINCREMENT, FacultyNumber INTEGER,SpecialityAndYear TEXT, Question TEXT, Answer TEXT, TestName TEXT, Grade TEXT, FOREIGN KEY(GradeID)REFERENCES Student_table(FacultyNumber))");
        db.execSQL("create table " + FifthTABLE_NAME + "(QuestionAnswerID INTEGER PRIMARY KEY AUTOINCREMENT,ID INTEGER,AnswerID INTEGER,FOREIGN KEY(ID)REFERENCES Student_table (ID), FOREIGN KEY (AnswerID) REFERENCES Answer_table(AnswerID))");
        db.execSQL("create table " + SeventhTABLE_NAME + "(AnswerID INTEGER PRIMARY KEY AUTOINCREMENT,QuestionID INTEGER, Answer TEXT, IsCorrect INTEGER)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'Не знам какво означава', 0)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'Прост,модерен,Обектно-ориентирен език за програмиране', 1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'Език,който се използва за command prompt',0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'1',0)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'3',1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'2',0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Клас в C#',0)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Логическо свързана група от класов в C#', 1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Не познавам такова понятие',0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Лесен за употреба език',1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Обектно-ориентиран език',1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Реалистичен език',0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'2', 0)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'4', 1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'6', 0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'Подмяна,Абстракция,Капсулация,Изтиване',0)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'Наследяване,Капсулация,Полиморфизъм,Абстракция',1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'Полиморфизъм,Подмяна,Изтриване,Регулиране',0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Позволено е в езици като C++',1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Не,не е позволено', 1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Да,позволено е', 0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'2', 0)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'3', 1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'4', 0)");

        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'Метод за работа в C#', 0)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'Един от принципите в ООП', 1)");
        db.execSQL("INSERT INTO " + SeventhTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10, 'Не знам какво е това', 0)");

        db.execSQL("create table " + NinethTABLE_NAME + "(AnswerID INTEGER PRIMARY KEY AUTOINCREMENT,QuestionID INTEGER, Answer TEXT, IsCorrect INTEGER)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'static',0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'void', 1)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(1,'Boolean',0)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'8 бита',0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2, '32 бита', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(2,'Не е дефиниран размера',1)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(3,'null', 1)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(3,'0.0',  0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(3, '0',   0)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Променливи, методи и конструктори, които са private могат да бъдат достъпни само от членовете на супер класа',1)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Променливи, методи и конструктори, които са protected могат да бъдат достъпни от подклас на супер класа',1)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(4,'Нито едно от горепосочените',0)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Не разбирам въпроса.', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Instance променливите са променливи дефинирани в методите и конструктурите на даден клас.',0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(5,'Instance променливите са нестатични променливи на даден клас,без да засягат методите в него',1)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'IS-A взаимоотношение', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'Не разбирам въпроса', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(6,'HAS-A взаимоотношение',1)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'Клас деклариран  с думата final е такъв клас',1)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7, 'Нито едното',0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(7,'Клас които не позволява наследяване', 1)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Не знам какво е това понятие', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'Възможноста всичко да се синхронизира', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(8,'това е възможноста за контрол на повече от една нишка да достъпи до споделени ресурси',1)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'Веднага щом обекта стане null',0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'Проверява се за null стойности през определено време', 1)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(9,'Когато обекта стане недосегаем',0)");

        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'Никое от изброените', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'StringBuilder', 0)");
        db.execSQL("INSERT INTO " + NinethTABLE_NAME + "(QuestionID, Answer,IsCorrect) VALUES(10,'StringBuffer',1)");

        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('1','1')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('2','2')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('3','3')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('4','4')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('5','5')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('6','6')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('7','7')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('8','8')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('9','9')");
        db.execSQL("INSERT INTO " + FifthTABLE_NAME + "(ID,AnswerID) VALUES('10','10')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FirstTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SecondTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ThirdTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FourthTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FifthTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SixthTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SeventhTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EightTABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + NinethTABLE_NAME);
        onCreate(db);
    }
}