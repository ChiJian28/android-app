package com.example.gramativ

object Constants {

    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getPythonQuestions(): ArrayList<QuestionQuizzes> {
        val questionsList = ArrayList<QuestionQuizzes>()

        // 1
        val que1 = QuestionQuizzes(
            1, "What is the correct way to declare a variable in Python?",
            "var x = 5",
            "x = 5",
            "5 = x",
            "x := 5",
            2
        )

        questionsList.add(que1)

        // 2
        val que2 = QuestionQuizzes(
            2,"What is the output of the following code? \n text = \"Hello, World!\"\n" +
                    "print(text[7:])",
            "World!",
            "ello, W",
            "eH!dlro",
            "Hello, ",
            1
        )

        questionsList.add(que2)

        // 3
        val que3 = QuestionQuizzes(
            3,"Question: Which of the following data types is used to store a sequence of characters in Python?",
            "int",
            "float",
            "str",
            "list",
            3
        )

        questionsList.add(que3)

        // 4
        val que4 = QuestionQuizzes(
            4,"What is the result of the following expression? \n 3 + 2 * 4",
            "20",
            "11",
            "14",
            "24",
            2
        )

        questionsList.add(que4)

        // 5
        val que5 = QuestionQuizzes(
            5,"How do you write a single-line comment in Python?",
            "// This is a comment",
            "# This is a comment",
            " /* This is a comment */",
            "<!-- This is a comment -->",
            2
        )

        questionsList.add(que5)

        // 6
        val que6 = QuestionQuizzes(
            6,"Which Python function is used to get the length of a list?",
            "len()",
            "count()",
            "size()",
            "length()",
            1
        )

        questionsList.add(que6)

        // 7
        val que7 = QuestionQuizzes(
            7,"How do you check if a value is present in a list in Python?",
            "value in list",
            "list.contains(value)",
            "list.has(value)",
            "contains(value, list)",
            1
        )

        questionsList.add(que7)

        // 8
        val que8 = QuestionQuizzes(
            8,"Which loop in Python is used to iterate over a sequence of items?",
            "do-while loop",
            "while loop",
            "repeat loop",
            "for loop",
            4
        )

        questionsList.add(que8)

        // 9
        val que9 = QuestionQuizzes(
            9,"How do you check if a number is even in Python?",
            "num % 2 == 0",
            "num.is_even()",
            "is_even(num)",
            "num // 2 == 0",
            1
        )

        questionsList.add(que9)

        // 10
        val que10 = QuestionQuizzes(
            10,"Which of the following is a Python data type used to store a sequence of characters?",
            "list",
            "tuple",
            "string",
            "dictionary",
            3
        )

        questionsList.add(que10)

        return questionsList
    }


    fun getHtmlQuestions(): ArrayList<QuestionQuizzes> {
        val questionsList = ArrayList<QuestionQuizzes>()

        // 1
        val que1 = QuestionQuizzes(
            1, "Which HTML element is used to define a hyperlink?",
            "<a>",
            "<link>",
            "<href>",
            "<hyperlink>",
            1
        )

        questionsList.add(que1)

        // 2
        val que2 = QuestionQuizzes(
            2,"What is the purpose of the HTML <img> element?",
            "To create a clickable link.",
            "To embed video content.",
            "To display an image on a webpage.",
            "To format text in bold.",
            3
        )

        questionsList.add(que2)

        // 3
        val que3 = QuestionQuizzes(
            3,"Which HTML element is used to create an ordered list?",
            "<ol>",
            "<list>",
            "<ul>",
            "<order>",
            1
        )

        questionsList.add(que3)

        // 4
        val que4 = QuestionQuizzes(
            4,"How do you add a comment in HTML?",
            "<!-- This is a comment -->",
            "// This is a comment",
            "/* This is a comment */",
            "** This is a comment **",
            1
        )

        questionsList.add(que4)

        // 5
        val que5 = QuestionQuizzes(
            5,"What is the correct HTML element to define a table?",
            "<table>",
            "<grid>",
            "<tab>",
            "<tbl>",
            1
        )

        questionsList.add(que5)

        // 6
        val que6 = QuestionQuizzes(
            6,"Which attribute is used to provide an alternative text for an image in HTML?",
            "alt",
            "caption",
            "description",
            "text",
            1
        )

        questionsList.add(que6)

        // 7
        val que7 = QuestionQuizzes(
            7,"How do you create an unordered list in HTML?",
            "<ol>",
            "<list>",
            "<ul>",
            "<order>",
            3
        )

        questionsList.add(que7)

        // 8
        val que8 = QuestionQuizzes(
            8,"What is the purpose of the HTML <audio> element?",
            "To display an audio player on the webpage.",
            "To embed video content.",
            "To create a clickable link.",
            "To display an image on a webpage.",
            1
        )

        questionsList.add(que8)

        // 9
        val que9 = QuestionQuizzes(
            9,"What does the HTML attribute \"href\" stand for in the <a> element?",
            "hyperlink reference",
            "heading reference",
            "hypertext reference",
            "HTML reference",
            3
        )

        questionsList.add(que9)

        // 10
        val que10 = QuestionQuizzes(
            10,"What is the correct HTML tag for creating a line break?",
            "<break>",
            "<br>",
            "<lb>",
            "<line>",
            2
        )

        questionsList.add(que10)

        return questionsList
    }


    fun getCssQuestions(): ArrayList<QuestionQuizzes> {
        val questionsList = ArrayList<QuestionQuizzes>()

        // 1
        val que1 = QuestionQuizzes(
            1, "Which CSS property is used to change the text color of an element?",
            "color",
            "text-color",
            "font-color",
            "text-style",
            1
        )

        questionsList.add(que1)

        // 2
        val que2 = QuestionQuizzes(
            2,"What is the correct CSS syntax to change the font size to 16 pixels?",
            "font-size: 16;",
            "font-size: 16px;",
            "size: 16px;",
            "text-size: 16;",
            2
        )

        questionsList.add(que2)

        // 3
        val que3 = QuestionQuizzes(
            3,"Which CSS property is used to set the size of the font?",
            "font-size",
            "text-size",
            "font-style",
            "text-font",
            1
        )

        questionsList.add(que3)

        // 4
        val que4 = QuestionQuizzes(
            4,"How do you select an element with the id \"header\" in CSS?",
            "#header",
            ".header",
            "element.header",
            "header#",
            1
        )

        questionsList.add(que4)

        // 5
        val que5 = QuestionQuizzes(
            5,"Which CSS property is used to add spacing between HTML elements?",
            "padding",
            "margin",
            "space",
            "gap",
            2
        )

        questionsList.add(que5)

        // 6
        val que6 = QuestionQuizzes(
            6,"What does CSS stand for?",
            "Cascading Style System",
            "Computer Style Sheets",
            "Colorful Style Sheets",
            "Cascading Style Sheets",
            4
        )

        questionsList.add(que6)

        // 7
        val que7 = QuestionQuizzes(
            7,"Which CSS property is used to make text bold?",
            "font-weight",
            "text-bold",
            "bold-text",
            "font-style",
            1
        )

        questionsList.add(que7)

        // 8
        val que8 = QuestionQuizzes(
            8,"How do you select all paragraph elements in CSS?",
            "<p> {}",
            ".paragraph {}",
            "#paragraph {}",
            "p {}",
            4
        )

        questionsList.add(que8)

        // 9
        val que9 = QuestionQuizzes(
            9,"How do you select all the <p> elements inside a <div> element in CSS?",
            "div p",
            "div > p",
            "div + p",
            "div ~ p",
            2
        )

        questionsList.add(que9)

        // 10
        val que10 = QuestionQuizzes(
            10,"How do you center an element horizontally in CSS?",
            "align: center;",
            "text-align: center;",
            "horizontal-align: center;",
            "center: horizontal;",
            2
        )

        questionsList.add(que10)

        return questionsList
    }


    fun getJsQuestions(): ArrayList<QuestionQuizzes> {
        val questionsList = ArrayList<QuestionQuizzes>()

        // 1
        val que1 = QuestionQuizzes(
            1, "What is the correct way to declare a variable in JavaScript?",
            "var = myVar;",
            "let myVar;",
            "variable myVar;",
            "const myVar;",
            2
        )

        questionsList.add(que1)

        // 2
        val que2 = QuestionQuizzes(
            2,"Which method is used to add elements to the end of an array in JavaScript?",
            "add()",
            "append()",
            "push()",
            "insert()",
            3
        )

        questionsList.add(que2)

        // 3
        val que3 = QuestionQuizzes(
            3,"What is the result of the following expression: 5 + \"2\" in JavaScript?",
            "7",
            "52",
            "3",
            "'52'",
            4
        )

        questionsList.add(que3)

        // 4
        val que4 = QuestionQuizzes(
            4,"How do you access the length of an array in JavaScript?",
            "array.size()",
            "array.length()",
            "array.count()",
            "array.size",
            2
        )

        questionsList.add(que4)

        // 5
        val que5 = QuestionQuizzes(
            5,"Which JavaScript function is used to convert a string to an integer?",
            "int()",
            "parseInteger()",
            "parseInt()",
            "toInteger()",
            3
        )

        questionsList.add(que5)

        // 6
        val que6 = QuestionQuizzes(
            6,"How do you convert a string to uppercase in JavaScript?",
            "string.toUpperCase()",
            "string.upper()",
            "string.toUpper()",
            "string.upperCase()",
            1
        )

        questionsList.add(que6)

        // 7
        val que7 = QuestionQuizzes(
            7,"How do you add an event listener to an HTML element in JavaScript?",
            "addEventListener()",
            "attachEvent()",
            "eventListener()",
            "onEvent()",
            1
        )

        questionsList.add(que7)

        // 8
        val que8 = QuestionQuizzes(
            8,"How do you access the first element of an array in JavaScript?",
            "array.first()",
            "array[0]",
            "array.firstElement()",
            "array.getElement(0)",
            2
        )

        questionsList.add(que8)

        // 9
        val que9 = QuestionQuizzes(
            9,"Which function is used to display a message in the browser console?",
            "alert()",
            "confirm()",
            "log()",
            "print()",
            3
        )

        questionsList.add(que9)

        // 10
        val que10 = QuestionQuizzes(
            10,"How do you comment a single line in JavaScript?",
            "/* This is a comment */",
            "// This is a comment",
            "<!-- This is a comment -->",
            "''' This is a comment '''",
            2
        )

        questionsList.add(que10)

        return questionsList
    }
}