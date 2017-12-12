package com.hortonworks.scythe

import java.text.SimpleDateFormat
import com.hortonworks.scythe.functions._
import java.util.Calendar;

class Sample {
  
  /**
   * "D" => Day, "H" => Hour, "M" => Minute, "S" => Second
   * agg => AVG, SUM, MIN, MAX, LAST
   */
  def downSample(rate: String, agg: String, s: List[Tuple2[java.util.Date, Double]]) : List[Tuple2[String, Double]] = {
    
    var df = new SimpleDateFormat("yyyy-MM-dd") // default rate
    rate match {
      case "Y" => df = new SimpleDateFormat("yyyy")
      case "Month" => df = new SimpleDateFormat("yyyy-MM")
      case "D" => df = new SimpleDateFormat("yyyy-MM-dd")
      case "H" => df = new SimpleDateFormat("yyyy-MM-dd HH")
      case "M" => df = new SimpleDateFormat("yyyy-MM-dd HH:mm")
      case "S" => df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    }
    
    val rtn = s.groupBy(f=> 
       df.format(f._1))
      .mapValues(values => values.map(_._2).toIterable.avg)
   
   rtn.toList 
  }
  
}