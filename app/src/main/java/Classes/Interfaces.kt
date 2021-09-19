package Classes

interface IStudent{
    val name: String
    val surname: String
    var groupNumber: Int
    var rating: Int

    fun study()

    fun getInfo() : String
}

interface IEmployee{
    val name: String
    val surname: String
    val employeeId: Int
    var salary: Int

    fun doWork()
    fun getInfo() : String
}