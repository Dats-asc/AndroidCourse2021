package Classes

class Employee(
    name: String,
    surname: String,
    age: Int,
    override val employeeId: Int,
    override var salary: Int
) : Person(name, surname, age), IEmployee{

    constructor(name: String, surname: String, age: Int, employeeId: Int, salary: Int, phoneNumber: String)
            : this(name, surname, age, employeeId, salary){
        this.phoneNumber = phoneNumber
    }

    override fun doWork() {
        println("I working!")
    }

    override fun getInfo(): String {
        return "Employee: $name $surname, age - $age, id - $employeeId"
    }

}