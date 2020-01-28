package work.lince.account.service

import spock.lang.Specification
import spock.lang.Unroll
import work.lince.commons.authentication.AuthenticationService
import work.lince.account.model.Account
import work.lince.account.model.AccountStatus
import work.lince.account.repository.AccountRepository
/**
 * @author pzatta
 */

class AccountServiceSpec extends Specification {

    AccountService service;

    def setup() {
        service = Spy(AccountService)
        service.repository = Mock(AccountRepository)
        service.authenticationService = Mock(AuthenticationService)

    }

    @Unroll
    def "verify with #title"() {
        given:
            1 * service.repository.save(_) >> { Account value ->
                value.id = id
                return value
            }
            1 * service.authenticationService.getAuthenticatedUser() >> { user }
            def account = new Account(
                title: title,
                status: status,
                owner: owner
            )
        when:
            def result = service.create(account)

        then:
            result != null
            result.id == id
            result.title == title
            result.owner == user
            result.status == AccountStatus.CREATED

        where:
            title             | status               | owner      | user   | id
            "Account Title 1" | null                 | "asdfasdf" | "asdf" | 1L
            "Account Title 2" | AccountStatus.CLOSED | null       | "qwer" | 2L
            "Account Title 3" | null                 | null       | "asdf" | 3L
            "Account Title 4" | AccountStatus.CLOSED | "asdfasdf" | "qwer" | 4L


    }

}