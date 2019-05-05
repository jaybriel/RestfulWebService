package restfulwebservice

import org.joda.time.LocalDate

class Workbook {
    Integer id
    String firstName
    String lastName
    LocalDate dateOfBirth
    int age
    String passportNumber
    String email
    String phone

    static constraints = {
        firstName nullable: false,blank: false,matches:"^([^0-9]*)\$"
        lastName nullable: false,matches:"^([^0-9]*)\$"
        dateOfBirth nullable: false
        age range: 18..65,nullable: false
        passportNumber blank:false,unique: true,nullable: false,matches:"^(?!0{9})[a-zA-Z0-9]{9}\$",maxSize: 9
        email email: true,nullable: false,unique: true,blank:false
        phone blank:false,nullable: false,matches:"[0-9]{10,12}",maxSize: 12
    }

    static hasMany = [workplaces: Workplace]
}
