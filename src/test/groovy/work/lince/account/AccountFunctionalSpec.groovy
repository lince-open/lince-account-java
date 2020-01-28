package work.lince.account

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import work.lince.account.model.Account
import work.lince.account.model.AccountStatus
import work.lince.account.repository.AccountRepository
/**
 * @author pzatta
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountFunctionalSpec extends Specification {

    @Shared
    RESTClient client

    @LocalServerPort
    int port;

    @Autowired
    AccountRepository accountRepository

    def setup() {
        client = new RESTClient("http://localhost:${port}/")
        client.contentType = ContentType.JSON
    }

    @Unroll
    def "get Success"() {
        given:
            accountRepository.save(new Account(title: title))

        when:
            def result = client.get(path: "accounts")

        then:
            result != null


        where:
            title            | _
            "Account Title 1" | _


    }


    @Unroll
    def "Create Projetc #title"() {
        given:
            def body = [
                title: title,
                status : status
            ]


        when:
            def result = client.post(path: "accounts", body: body, headers: ["lince.user.name": userName])

        then:
            result != null
            result.data.id != null
            result.data.title == title
            result.data.status == AccountStatus.CREATED.toString()
            result.data.owner == expectedOwner

        where:
            title             | userName   | status                || expectedOwner
            "Account Title 1" | null       | null                  || 'anonymous'
            "Account Title 2" | 'x1324'    | AccountStatus.CREATED || 'x1324'
            "Account Title 3" | 'zxcvasdf' | AccountStatus.CLOSED  || 'zxcvasdf'


    }

}