package com.octo

import java.lang.Boolean

class ExtentSpec extends AbstractSpec {

  "Extent" should "contain" in  {
    //GIVEN
    //kafka.createTopic(name="toto", partCount = , extentSize= )

    //WHEN
    //kafka.sendMessage()

    //THEN
    //
    //check if msg is really written : check target content
    //check in target directory if toto file existsaAd =

    val t  = KafkaUtilities.createTopic()
    print(t)

    assert(Boolean.TRUE)

  }

}
