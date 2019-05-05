package restfulwebservice

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class WorkplaceSpec extends Specification implements DomainUnitTest<Workplace> {

    def setup() {
    	mockDomain Workplace
    }

    def cleanup() {
    }


     void 'test cmpCode cannot be blank then validate value'() {
     when:
     domain.cmpCode = ''

     then:
     !domain.validate(['cmpCode'])
    }

     void 'test cmpCode cannot be null then validate value'() {
     when:
     domain.cmpCode = null

     then:
     !domain.validate(['cmpCode'])
    }

        void 'test cmpName can be null'() {
        when:
        domain.cmpName = null

        then:
        domain.validate(['cmpName'])
    }

        void 'test cmpName can be blank'() {
        when:
        domain.cmpName = ''

        then:
        domain.validate(['cmpName'])
    }
        void 'test ctyCode cannot be blank'() {
        when:
        domain.ctyCode = ''

        then:
        !domain.validate(['ctyCode'])
    }

        void 'test ctyCode cannot be null'() {
        when:
        domain.ctyCode = null

        then:
        !domain.validate(['ctyCode'])
    }
        void 'test ctyDesc can be null'() {
        when:
        domain.ctyDesc = null

        then:
        domain.validate(['ctyDesc'])
    }

        void 'test ctyDesc can be blank'() {
        when:
        domain.ctyDesc = ''

        then:
        domain.validate(['ctyDesc'])
    }
}
