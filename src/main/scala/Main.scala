
import java.sql._


object Main extends App {
  var con: Connection = _

  try {
    val connectionString = "jdbc:hive2://localhost:10000/wiki"
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    con = DriverManager.getConnection(connectionString, "", "")

    // val statement = con.createStatement()
    // var resultSet = statement.executeQuery("SELECT * FROM 2020_01 LIMIT 10")

    // while (resultSet.next) {
    //   //val foo = resultSet.getString("foo")
    //   //val bar = resultSet.getString("bar")
    //   //resultSet.
    //   println(resultSet)
    // }    
  } catch {
    case e: Exception => {
      e.printStackTrace()
      println("Hive database couldn't be connected to.")
    }
  } 

  if( con != null){
    sql_run()
    con.close();
  }


  def sql_run():Unit = {
    var h_util = new Hive_util(con)
    var user_input = ""

    while(user_input != "q"){
      println("-----------------------------")
      println("Usage:")
      println("[X]: Checks both")
      println("[Xa]: Checks wiki to wiki")
      println("[Xb]: Checks anything to wiki")
      println("[Xc]: Checks wiki link")
      println("[Xd]: Checks wiki page")
      println("[Xe]: Checks out of wiki page")
      println("-----------------------------")
      println("[1]: Jan")
      println("[2]: Feb")
      println("[3]: Mar")
      println("[4]: Apr")
      println("[5]: May")
      println("[6]: Jun")
      println("[7]: ALL")
      println("[q]: Quit")

      user_input = scala.io.StdIn.readLine()
      
      // Check user input
      user_input match {
        case "1" => {
          h_util.most_clicked_wiki_wiki("2020_01")
          println("\n\n")
          h_util.most_clicked_anything_wiki("2020_01")
          println("\n\n")
          h_util.most_clicked_wiki_link("2020_01")
          println("\n\n")
          h_util.most_clicked_wiki_page("2020_01")
          println("\n\n")
          h_util.most_clicked_out_wiki("2020_01")
        }
        case "2" => {
          h_util.most_clicked_wiki_wiki("2020_02")
          println("\n\n")
          h_util.most_clicked_anything_wiki("2020_02")
          println("\n\n")
          h_util.most_clicked_wiki_link("2020_02")
          println("\n\n")
          h_util.most_clicked_wiki_page("2020_02")
          println("\n\n")
          h_util.most_clicked_out_wiki("2020_02")
        }
        case "3" => {
          h_util.most_clicked_wiki_wiki("2020_03")
          println("\n\n")
          h_util.most_clicked_anything_wiki("2020_03")
          println("\n\n")
          h_util.most_clicked_wiki_link("2020_03")
          println("\n\n")
          h_util.most_clicked_wiki_page("2020_03")
          println("\n\n")
          h_util.most_clicked_out_wiki("2020_03")
        }
        case "4" => {
          h_util.most_clicked_wiki_wiki("2020_04")
          println("\n\n")
          h_util.most_clicked_anything_wiki("2020_04")
          println("\n\n")
          h_util.most_clicked_wiki_link("2020_04")
          println("\n\n")
          h_util.most_clicked_wiki_page("2020_04")
          println("\n\n")
          h_util.most_clicked_out_wiki("2020_04")
        }
        case "5" => {
          h_util.most_clicked_wiki_wiki("2020_05")
          println("\n\n")
          h_util.most_clicked_anything_wiki("2020_05")
          println("\n\n")
          h_util.most_clicked_wiki_link("2020_05")
          println("\n\n")
          h_util.most_clicked_wiki_page("2020_05")
          println("\n\n")
          h_util.most_clicked_out_wiki("2020_05")
        }
        case "6" => {
          h_util.most_clicked_wiki_wiki("2020_06")
          println("\n\n")
          h_util.most_clicked_anything_wiki("2020_06")
          println("\n\n")
          h_util.most_clicked_wiki_link("2020_06")
          println("\n\n")
          h_util.most_clicked_wiki_page("2020_06")
          println("\n\n")
          h_util.most_clicked_out_wiki("2020_06")
        }
        case "7" => {
          h_util.most_clicked_overall_wiki_wiki()
          println("\n\n")
          h_util.most_clicked_overall_anything_wiki()
          println("\n\n")
          h_util.most_clicked_overall_wiki_link()
          println("\n\n")
          h_util.most_clicked_overall_wiki_page()
          println("\n\n")
          h_util.most_clicked_overall_out_wiki_link()
        }
        
        case "1a" => h_util.most_clicked_wiki_wiki("2020_01")
        case "2a" => h_util.most_clicked_wiki_wiki("2020_02")
        case "3a" => h_util.most_clicked_wiki_wiki("2020_03")
        case "4a" => h_util.most_clicked_wiki_wiki("2020_04")
        case "5a" => h_util.most_clicked_wiki_wiki("2020_05")
        case "6a" => h_util.most_clicked_wiki_wiki("2020_06")
        case "7a" => h_util.most_clicked_overall_wiki_wiki()

        case "1b" => h_util.most_clicked_anything_wiki("2020_01")
        case "2b" => h_util.most_clicked_anything_wiki("2020_02")
        case "3b" => h_util.most_clicked_anything_wiki("2020_03")
        case "4b" => h_util.most_clicked_anything_wiki("2020_04")
        case "5b" => h_util.most_clicked_anything_wiki("2020_05")
        case "6b" => h_util.most_clicked_anything_wiki("2020_06")
        case "7b" => h_util.most_clicked_overall_anything_wiki()

        case "1c" => h_util.most_clicked_wiki_link("2020_01")
        case "2c" => h_util.most_clicked_wiki_link("2020_02")
        case "3c" => h_util.most_clicked_wiki_link("2020_03")
        case "4c" => h_util.most_clicked_wiki_link("2020_04")
        case "5c" => h_util.most_clicked_wiki_link("2020_05")
        case "6c" => h_util.most_clicked_wiki_link("2020_06")
        case "7c" => h_util.most_clicked_overall_wiki_link()

        case "1d" => h_util.most_clicked_wiki_page("2020_01")
        case "2d" => h_util.most_clicked_wiki_page("2020_02")
        case "3d" => h_util.most_clicked_wiki_page("2020_03")
        case "4d" => h_util.most_clicked_wiki_page("2020_04")
        case "5d" => h_util.most_clicked_wiki_page("2020_05")
        case "6d" => h_util.most_clicked_wiki_page("2020_06")
        case "7d" => h_util.most_clicked_overall_wiki_page()

        case "1e" => h_util.most_clicked_out_wiki("2020_01")
        case "2e" => h_util.most_clicked_out_wiki("2020_02")
        case "3e" => h_util.most_clicked_out_wiki("2020_03")
        case "4e" => h_util.most_clicked_out_wiki("2020_04")
        case "5e" => h_util.most_clicked_out_wiki("2020_05")
        case "6e" => h_util.most_clicked_out_wiki("2020_06")
        case "7e" => h_util.most_clicked_overall_out_wiki_link()
        
        case "q" => println("Quitting")
      }
    }
  }

}