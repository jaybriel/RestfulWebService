package restfulwebservice

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Unroll

class WorkbookSpec extends Specification implements DomainUnitTest<Workbook> {

    def setup() {
        mockDomain Workbook
    }

    def cleanup() {
    }

    void "test email validation with different test inputs and output expected result"(){
        when:
        domain.email = value

        then:
        expected == domain.validate(['email'])
        domain.errors['email']?.code == expectedErrorCode

        where:
        value                   | expected | expectedErrorCode
         null                   | false    | 'nullable'
        ''                      | false    |  'blank'
        'contact@hilton.com'    | true     |   null
        'hilton'                | false    | 'email.invalid'
    }
    /*
    void "test firstName cannot be null and output expected result"(){
    when:
    domain.firstName = value

    then:
    expected == domain.validate(['firstName'])
    domain.errors['firstName']?.code == expectedErrorCode

    where:
        value                   | expected | expectedErrorCode
         null                   | false    | 'nullable'
        ''                      | false    |  'blank'
        'jaybrielsomcio'        | true     |   null
        'hilton'                | true     | 'null'
    }
    */

     void 'test name cannot be blank then validate value'() {
     when:
     domain.firstName = ''

     then:
     !domain.validate(['firstName'])
    }

     void 'test name cannot be null then validate value'() {
     when:
     domain.firstName = null

     then:
     !domain.validate(['firstName'])
    }

        void 'test date of birth cannot be null'() {
        when:
        domain.dateOfBirth = null

        then:
        !domain.validate(['dateOfBirth'])
    }

        void 'test passportNumber cannot be blank'() {
        when:
        domain.passportNumber = ''

        then:
        !domain.validate(['passportNumber'])
    }
        void 'test passportNumber cannot be null'() {
        when:
        domain.passportNumber = null

        then:
        !domain.validate(['passportNumber'])
    }

        void 'test phone cannot be null'() {
        when:
        domain.phone = ''

        then:
        !domain.validate(['phone'])
    }

    void "test age range validation with different test inputs and ouput expected result"(){
        when:
        domain.age = value

        then:
        expected == domain.validate(['age'])
        domain.errors['age']?.code == expectedErrorCode

        where:
        value                  | expected | expectedErrorCode
        18                     | true     | null
        55                     | true     | null
        30                     | true     | null
        70                     | false    | 'range.toobig'
        23                     | true     | null
        15                     | false    | 'range.toosmall'
        90                     | false    | 'range.toobig'
    }




}
