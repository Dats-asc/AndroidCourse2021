package Classes

class Student(
    name: String,
    surname: String,
    age: Int,
    override var groupNumber: Int,
    override var rating: Int,
) : Person(name, surname, age), IStudent{

    constructor(name: String, surname: String, age: Int, groupNumber: Int, rating: Int, phoneNumber: String)
            : this(name, surname, age, groupNumber, rating){
        this.phoneNumber = phoneNumber
    }

    override fun study() {
        print("I study!")
    }

    override fun getInfo() : String {
        return "Student: $name $surname, age: $age, group - $groupNumber, rating - $rating"
    }

}