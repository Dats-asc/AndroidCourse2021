package Classes

/*
* Нужно будет создать класс с несколькими полями, добавить ему 2 наследникjd,
* а наследники имплементирует какой-то интерфейс.
* У каждого наследника свой интервес. Объекты создавайте в onCreate у MainActivity и
* выводите результаты с помощью println("Some message") в логи
*/

open class Person(
    val name: String,
    val surname: String,
    var age: Int,
){
    var phoneNumber: String? = null

    constructor(name: String, surname: String, age: Int,phoneNumber: String) : this(name, surname, age){
        this.phoneNumber = phoneNumber
    }
}