import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.*;


@Path("social")
public class Hallo {
	


	
		@GET
		@Path("user/{user}")
		@Produces("text/plain")
		public String welcomeUser(@PathParam("user") String user)
		{
			return String.format("Wilkommen: " + user);
		}
		
		@POST
		@Path("user/{user}")
		@Produces("text/plain")
		public String cerateUser(@PathParam("user") String user)
		{
			
			 PrintWriter writer;
			 FileWriter fileWriter;
			 BufferedWriter bufferedWriter;
			 Boolean folder = new File("Users\\"+user).mkdir(); 
			 if(!folder)
			 {
				 return "Ordner konnte nicht erstellt werden!";
			 }
			 File file = new File ("Users/"+user+"/"+user+".txt");
			try {
				 
					fileWriter = new FileWriter(file , true);
					bufferedWriter = new BufferedWriter(fileWriter);
					writer= new PrintWriter(bufferedWriter);
					writer.append(user);
					
			
					  writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   return "User: "+user+" hinzugefügt!";
			  
		}
		
		@GET
		@Path("user/{user}/friends")
		@Produces("text/plain")
		public String getFriends(@PathParam("user") String user)
		{
			FileReader fileReader= null;
			BufferedReader bufferedReader= null;
			String row = null;
			StringBuilder stringBuilder= new StringBuilder();
			String all="Error";
			try {
				fileReader = new FileReader("Users/"+user+"/"+user+".txt");
				bufferedReader= new BufferedReader(fileReader);
				row = bufferedReader.readLine();

			    while (row != null) {
			    	if(row.equals(user))
			    	{
				        row = bufferedReader.readLine();
			    	}
			    	else
			    	{
				    	stringBuilder.append(row);
				    	stringBuilder.append(System.lineSeparator());
				        row = bufferedReader.readLine();
			    	}
			    }
				 all= stringBuilder.toString();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     return "Die Freunde von " +user+ " sind: \n"+ all;
		}
		
		
		@POST
		@Path("user/{user}/friends/{friend}")
		@Produces("text/plain")
		public String addFriends(@PathParam("user") String user, @PathParam("friend") String friend)
		{
			PrintWriter writer;
			 FileWriter fileWriter;
			 BufferedWriter bufferedWriter;
			 File file = new File ("Users/"+user+"/"+user+".txt");
			try {
				
					fileWriter = new FileWriter(file , true);
					bufferedWriter = new BufferedWriter(fileWriter);
					writer= new PrintWriter(bufferedWriter);
					writer.append(System.lineSeparator());
					writer.append(friend);
					
			
					writer.close();
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   return "Freund: "+friend+" wurde bei User: "+user+" hinzugefügt!";
			  
		}
		
		
		@POST
		@Path("user/{user}/posts/{post}")
		@Produces("text/plain")
		public String addPost(@PathParam("user") String user, @PathParam("post") String post)
		{
			PrintWriter writer;
			 FileWriter fileWriter;
			 BufferedWriter bufferedWriter;
			 Boolean folder = new File("Users/"+user+"\\Posts").mkdir(); 
			 if(!folder)
			 {
				 return "Ordner konnte nicht erstellt werden!";
			 }
			 File file = new File ("Users/"+user+"/"+"Posts/"+user+".txt");
			try {
				
					fileWriter = new FileWriter(file , true);
					bufferedWriter = new BufferedWriter(fileWriter);
					writer= new PrintWriter(bufferedWriter);
					writer.append(System.lineSeparator());
					writer.append(post);
					
			
					writer.close();
			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   return "Post: "+post+" wurde bei User: "+user+" hinzugefügt!";
			  
		}
	
		@GET
		@Path("user/{user}/posts")
		@Produces("text/plain")
		public String getPosts(@PathParam("user") String user)
		{
			FileReader fileReader= null;
			BufferedReader bufferedReader= null;
			String row = null;
			StringBuilder stringBuilder= new StringBuilder();
			String allPosts="Error";
			try {
				fileReader = new FileReader("Users/"+user+"/Posts/"+user+".txt");
				bufferedReader= new BufferedReader(fileReader);
				row = bufferedReader.readLine();
				
			    while (row != null) {
			    	
			
				    	stringBuilder.append(row);
				    	stringBuilder.append(System.lineSeparator());
				        row = bufferedReader.readLine();
	
			    }
				 allPosts= stringBuilder.toString();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    try {
				bufferedReader.close();
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     return "Die Posts von " +user+ " sind: \n"+ allPosts;
		}
	
}

