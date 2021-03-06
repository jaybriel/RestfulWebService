package restfulwebservice

import grails.plugins.jodatime.binding.JodaTimePropertyEditorRegistrar
import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification


class WorkbookServiceSpec extends Specification implements ServiceUnitTest<WorkbookService>, DataTest{

    @Autowired WorkbookService workbookservice

    def setup() {
        mockDomain Workbook
    }

    def cleanup() {
    }


    void "test save method with existing workbook then returns the saved workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:
        result = service.save(workbook)

        then:
        result == workbook
    }

    void "test retrieveId action of workbook then returns the workbook that matches the given id parameters"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:
        result = service.retrieveId(1)

        then:
        result.firstName =="jaybriel"
        result.lastName == "somcio"
        result.age == 21
        result.passportNumber == "E12345678"
        result.email == "jaybrielsomcio@gmail.com"
        result.phone == "09452665267"

    }

    void "test delete action with existing workbook then should delete the existing workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"jaybriel",lastName:"somcio",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def result

        when:
        result = service.delete(workbook)

        then:
        result ==  null
    }

        void 'test the list action with 3 existing workbooks then returns the correct model with size of list'(){

        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook1 = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        def workbook2 = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12445678",email: "jaybrielsomcio@yahoo.com",phone: "09452665267")
        def workbook3 = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E22345678",email: "jaybrielsomcio@hotmail.com",phone: "09452665267")
        def result

        when:
        service.save(workbook1)
        service.save(workbook2)
        service.save(workbook3)
        result = service.list()

        then:
        result.size() == 3

    }
}
