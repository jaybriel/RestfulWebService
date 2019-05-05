package restfulwebservice

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class WorkbookControllerSpec extends Specification implements ControllerUnitTest<WorkbookController>{

    def setup() {
    
    }

    def cleanup() {
    }
/*

    def 'test the index action then returns the correct model with size of list'(){

    	given:
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
		List<Workbook> workbooks = [new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267"),
							new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12445678",email: "jaybrielsomcio@yahoo.com",phone: "09452665267"),
							new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E22345678",email: "jaybrielsomcio@hotmail.com",phone: "09452665267")]

	    controller.workbookService = Stub(WorkbookService)
	    {
	    	list(_) >> workbooks
	    	count() >> workbooks.size()
	    }

	    when:
	    controller.index()

	    then:
	    model.workbooks
	    model.workbooks.size() == 3

    }
*/

    def 'test the save action with existing workbook then returns successful result'(){
    	DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd")
    	controller.workbookService = Stub(WorkbookService){
    		save(_,_) >> new Workbook(firstName:"test",lastName:"test",dateOfBirth: LocalDate.parse('1997-11-02',formatter),age: 21,passportNumber: "E12345678",email: "jaybrielsomcio@gmail.com",phone: "09452665267")

    	}

    	when:
    	request.method = 'POST'
    	params
    	controller.save()

    	then:
    	response.status == 200
    }


}
