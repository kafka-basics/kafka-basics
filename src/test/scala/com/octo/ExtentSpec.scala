package com.octo

import java.io.File
import java.lang.Boolean



class ExtentSpec extends AbstractSpec {

  "Create topic with 3 partitions" should "create 3 directories" in  {
    //GIVEN
    //kafka.createTopic(name="toto", partCount = , extentSize= )

    //WHEN
    //kafka.sendMessage()

    //THEN
    //
    //check if msg is really written : check target content
    //check in target directory if toto file existsaAd =

    val t  = KafkaUtilities.createTopic()
    val d = new File("../../../target/data")
    if (d.exists && d.isDirectory) {
      val files = d.listFiles.filter(_.getName.startsWith("new-topic-test-"))
      assert(3==files.length)

    }
    //KafkaUtilities.sendMessage()
    //print(t)



  }

}
