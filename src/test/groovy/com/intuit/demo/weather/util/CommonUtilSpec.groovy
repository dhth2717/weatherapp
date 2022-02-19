package com.intuit.demo.weather.util

import spock.lang.Specification


class CommonUtilSpec extends Specification{

    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }


}
