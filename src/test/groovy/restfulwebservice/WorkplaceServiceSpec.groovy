package restfulwebservice

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import spock.lang.Specification

class WorkplaceServiceSpec extends Specification implements ServiceUnitTest<WorkplaceService>, DataTest{

    def setup() {
        mockDomain Workplace
    }

    def cleanup() {
    }
/*
    void "test save method with existing workplace and workbook"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267").save(flush:true)
        def workplace = new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter)).save(flush:true)
        def result
    }*/

    void "test validateDate with existing workbook and workplace to validate startdate and enddate if overlaps and returns expected boolean result"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
        def workplace = new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))
        def result

        when:
        result = service.validateDate(workbook,workplace)

        then:"boolean is true if dates doesnt overlap"
        result == true
    }

    void "test checkOverlapWithPrevDate with existing workbook and multiple workplace to validate startdate and enddate if overlaps with other entries and returns expected boolean result"(){
        given:
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
                    .addToWorkplaces(new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))).save(flush:true)
        def workplace = new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))
        def result

        when:
        result = service.checkOverlapDateWithPrevDate(workbook,workplace,workbook.workplaces.size()-1)

        then:
        result == false
    }

    void "test validateWorkplace with 1 existing workbook and workplace to validate constraints then returns boolean result"(){
        given:
           DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
        def workbook = new Workbook(id:1,firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")
                    .addToWorkplaces(new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2019-01-02",formatter),endDate: LocalDate.parse("2019-01-30",formatter))).save(flush:true)
        def workplace = new Workplace(cmpCode:"IBM",cmpName:"Webb Fontaine Group",ctyCode:"MNL",ctyDesc:"ManilaArea",startDate: LocalDate.parse("2018-01-02",formatter),endDate: LocalDate.parse("2018-01-30",formatter))
        def result

        when:
        result = service.validateWorkplace(workbook,workplace)

        then:"returns true if workplace constraints is met and dates doesn't overlap"
        result == true
    }

}
