import java.util.Scanner;
class Storage
{
	int index;
	Storage next;
	public Storage(int index)
	{
		this.index=index;
		next=null;
	}
}
class List
{
	Storage source=null;
	Storage destination=null;
	int storageCounter=0;
	public List()
	{
		storageCounter=0;
	}
	public void add(int index)
	{
		Storage temp=new Storage(index);
		if (source==null)
		{
			source=temp;
			destination=temp;
		}
		else
		{
			destination.next=temp;
			destination=temp;
		}
		storageCounter++;
	}
	public int grab()
	{
		if (storageCounter==0)
		{
			return -1;
		}
		else
		{
			int temp=source.index;
			source=source.next;
			storageCounter--;
			return temp;
		}
	}
	public int getDestination()
	{
		return destination.index;
	}
	public void clear()
	{
		source=null;
		destination=null;
		storageCounter=0;
	}
	public void showStorage()
	{
		Storage ptr=source;
		for (int count=0;count<storageCounter;count++)
		{
			System.out.printf("%d\n", ptr.index);
			ptr=ptr.next;
		}
	}
	public boolean isEmpty()
	{
		if (storageCounter==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int storageSize()
	{
		return storageCounter;
	}
}
class Node
{
	List path;
	int data;
	int listIndex;
	Node next;
	public Node(List path,int data,int listIndex)
	{
		this.listIndex=listIndex;
		this.path=path;
		this.data=data;
		next=null;
	}
}
class LinkedQ
{
	Node head=null;
	Node tail=null;
	
	int counter=0;
	public void enque(List path,int data,int listIndex)
	{
		Node temp=new Node (path,data,listIndex);
		if (head==null)
		{
			head=temp;
			tail=temp;
		}
		else
		{
			Node ptr=head;
			int flag=0;
			Node temp1=ptr;
			for (int count=0;count<counter;count++)
			{
				if (temp.data<head.data)
				{
					temp.next=head;
					head=temp;
					break;
				}
				if (temp.data<ptr.data)
				{
					temp1.next=temp;
					temp.next=ptr;
					break;
				}
				temp1=ptr;
				if (ptr.next==null)
				{
					//System.out.printf("%d", temp.data);
					flag=1;
					break;
				}
				ptr=ptr.next;
			}
			if (flag==1)
			{
			tail.next=temp;
			tail=temp;
			}
			
		}
		counter++;
	}
	
	public Node deque()
	{
		if (counter==0)
		{
			return null;
		}
		else
		{
			Node temp=head;
			head=head.next;
			counter--;
			return temp;
		}
	}
	public int peek()
	{
		if (counter==0)
		{
			return -1;
		}
		else
		{
			return head.data;
		}
	}
	public void show()
	{
		Node ptr=head;
		for (int count=0;count<counter;count++)
		{
			System.out.printf("%d\n", ptr.data);
			ptr=ptr.next;
		}
	}
	
}
class Vertex
{
	String label;
	int heuristic;
	public Vertex(String label,int heuristic)
	{
		this.label=label;
		this.heuristic=heuristic;
	}
}
class Graph
{
	Vertex[] array;
	int counter=0;
	int[][] matrix;
	boolean[] visited;
	int[][] adjencyMatrix;
	public Graph(int index)
	{
		array=new Vertex[index];
		adjencyMatrix=new int[index][index];
		visited=new boolean[index];
	}
	public void insert(String data,int heuristic)
	{
		array[counter]=new Vertex(data,heuristic);
		counter++;
	}
	public void insertEdge(int temp1,int temp2)
	{
		adjencyMatrix[temp1][temp2]=1;
		adjencyMatrix[temp2][temp1]=1;
	}
	public boolean hurdle(String source,String destination)
	{
		int temp1=0;
		int temp2=0;
		adjencyMatrix[temp1][temp2]=2;
		adjencyMatrix[temp2][temp1]=2;
		for (int count=0;count<array.length;count++)
		{
			if (array[count].label.equals(source))
			{
				temp1=count;
			}
			if (array[count].label.equals(destination))
			{
				temp2=count;
			}
		}
		if (adjencyMatrix[temp1][temp2]==1)
		{	
			adjencyMatrix[temp1][temp2]=2;
			adjencyMatrix[temp2][temp1]=2;
			return true;
		}
		else
		{
			return false;
		}
	}
	public void displayMatrix()
	{
		for (int count=0;count<counter;count++)
		{
			System.out.printf("\t%s",array[count].label);
		}
		System.out.printf("\n\n");
		for (int row=0;row<counter;row++)
		{
			System.out.printf("%s\t", array[row].label);
			for (int column=0;column<counter;column++)
			{
				System.out.printf("%d\t", adjencyMatrix[row][column]);
			}
			System.out.print("\n\n");
		}
	}
	public void display()
	{
		for (int row=0;row<counter;row++)
		{
			System.out.printf("%s",array[row].label);
			for (int column=0;column<counter;column++)
			{
				
				if (adjencyMatrix[row][column]==1)
				{
					System.out.printf("-> %s",array[column].label);
				}
			}
				System.out.print("\n\n\n");
		}
	}
	public void setEdges(int size)
	{
		matrix= new int[size][size];
		
		matrix[0][1]=2;
		matrix[1][0]=2;
		insertEdge(0,1);
		
		matrix[0][3]=5;
		matrix[3][0]=5;//
		insertEdge(0,3);
		
		matrix[1][4]=4;
		matrix[4][1]=4;//
		insertEdge(1,4);
		
		matrix[2][5]=14;
		matrix[5][2]=14;
		insertEdge(2,5);
		
		
		matrix[1][2]=3;
		matrix[2][1]=3;
		insertEdge(1,2);
		
		
		matrix[3][2]=22;
		matrix[2][3]=22;//
		insertEdge(2,3);
		
		matrix[4][5]=9;
		matrix[4][5]=9;
		insertEdge(4,5);
	}
	
	public void aStar(String source,String destination)// add source and destination
	{
		int actualSource=0;
		int actualDestination=0;
		for (int count=0;count<array.length;count++)
		{
			if (array[count].label.equals(source))
			{
				actualSource=count;
			}
			if (array[count].label.equals(destination))
			{
				actualDestination=count;
			}
		}
		
		array[actualDestination].heuristic=0;
		LinkedQ my=new LinkedQ();
		int linkedList=0;
		List[] list=new List[7]; // edges add krnay
		for (int count=0;count<array.length;count++)
		{
			list[count]=new List();
			
		}
		for (int count=0;count<array.length;count++)
		{
			if (adjencyMatrix[actualSource][count]==1)
			{
				int heuristic= matrix[actualSource][count]+array[count].heuristic;
				list[linkedList].add(actualSource);//
				list[linkedList].add(count);// 
				my.enque(list[linkedList],heuristic,linkedList); // 
				linkedList++;
			}
		}
		visited[actualSource]=true;
		while(true)
		{
		Node temp=my.deque();
		int check=temp.path.getDestination();//temp={path=a->b,6,0}
		if (check==actualDestination)
		{
		int[] arr=new int[temp.path.storageSize()];
		for (int count=0;count<arr.length;count++)
		{
			arr[count]=temp.path.grab();
			System.out.printf("%s->", array[arr[count]].label);
		}
			System.out.printf("=ShortestPath=%dKM\n", temp.data);
			break;
		}
		int[] arr=new int[temp.path.storageSize()];
		int heuristic=0;
		for (int count=0;count<arr.length;count++)
		{
			arr[count]=temp.path.grab();
			
		}
		
		list[temp.listIndex].clear();
		int tempInd=temp.listIndex;
		for (int count=0;count<array.length;count++)
		{
			heuristic=0;
			if (adjencyMatrix[check][count]==1)
			{
				if (visited[count]==false)
				{
					int a=0;
				for (int count1=0;count1<arr.length-1;count1++)
				{
					heuristic = heuristic+ matrix[arr[count1]][arr[count1+1]];
					list[tempInd].add(arr[count1]);
					a=count1+1;
				}
				list[tempInd].add(arr[a]);
				heuristic = heuristic+ matrix[check][count];
				heuristic=heuristic+array[count].heuristic;
				list[tempInd].add(count);
				my.enque(list[tempInd],heuristic,tempInd);
				for (int i=0;i<list.length;i++)
				{
					if (list[i].isEmpty()==true)
					{
						tempInd=i;
						break;
					}	 
				}
				}
			}
		}
		visited[check]=true;
		}
		
		
			
	}
}
public class Random
{
	public static void main(String[] args)
	{
		Scanner input=new Scanner(System.in);
		String[] temp={"lahore","islamabad","karachi","peshawar","quetta","multan"};
		Graph my =new Graph(temp.length);
		 //inserting cities
		int even=2;
		for (int count=0;count<temp.length;count++)
		{
			my.insert(temp[count],even);
			even=even+2;
		}	
		my.setEdges(temp.length);// giving cities
		//my.displayMatrix();
		System.out.print("Enter Source:");
		String source=input.nextLine();
		String destination="a";
		String source1="a";
		String destination1="a";
		int flag=0;
		for (int count=0;count<temp.length;count++)
		{
			if (temp[count].equals(source))
			{
				System.out.print("Enter Destination:");
				destination=input.nextLine();
				for (int count1=0;count1<temp.length;count1++)
				{
					if (temp[count1].equals(destination))
					{
						flag=1;
						break;
					}
				}
			break;
			}
		}
		if (flag==0)
		{
			System.out.print("Wrong input");
			System.exit(0);
		}
		
		System.out.printf("\n1) Press 1 for Enter Hurdle\n2) Press 2 Continue:\n");
		int hur=input.nextInt();
		flag=0;
		if(hur ==1)
		{
			input.nextLine();
			System.out.print("Enter Source:");
			source1=input.nextLine();
			for (int count=0;count<temp.length;count++)
			{
				if (temp[count].equals(source1))
				{
					System.out.print("Enter Destination:");
					destination1=input.nextLine();
					for (int count1=0;count1<temp.length;count1++)
					{
						if (temp[count1].equals(destination1))
						{
							flag=1;
							break;
						}
					}
					break;
				}
			}
			if (flag==0)
			{
			System.out.print("Wrong input");
			System.exit(0);
			}
			
		if (my.hurdle(source1,destination1)==true)
		{
			System.out.print("Hurdle successfully added...\n");
		}
		else
		{
			System.out.print("Hurdle not successfully added...\n");
		}
		}
		
			my.aStar(source,destination);
		
	}
}

/*...............implemented graph...............*/
	/*
			lahore(2)-------------------2----------islamabad(4)------------3-------------------karachi(6)
			|										   |                                         /|					
			|										   |										/ |
			|		  /-----------------22-------------|-------------------------------------- /  |
			5		/ 	    						   4									  	  14
			|	  / 		   						   |										  |
			|	/		 							   |										  |
			| /										   |										  |			
			peshawar(8)								quetta(10)-------------------9--------------multan(12)				
			
			Changes you do in program:-
			*add more vertex.
			*change weight and heuristic value.
			*add more edges.
			
			//this graph is hard coded in this code.
	*/