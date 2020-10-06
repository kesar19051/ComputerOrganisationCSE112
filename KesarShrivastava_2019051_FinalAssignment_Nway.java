import java.util.*;
public class KesarShrivastava_2019051_FinalAssignment_Nway {
	public static Queue<Integer> q = new LinkedList<>(); 
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter number of addresses: ");
		int numOfWords = in.nextInt();
		
		System.out.print("Enter s: ");
		int s = in.nextInt();
		
		System.out.print("Enter cl: ");
		int cl = in.nextInt();
		
		System.out.print("Enter b: ");
		int b = in.nextInt();
		
		System.out.print("Enter n: ");
		int k = in.nextInt();
		
		int numOfBlock = numOfWords/b;
		int[][] blocks = new int[numOfBlock][b];

		int numSet = cl/k;
		int[][] set = new int[numSet][k];
		for(int i = 0; i<numSet; i++) {
			for(int j = 0; j<k; j++) {
				set[i][j] = -1;
			}
		}
		
		System.out.print("Number of operations: ");
		int numOfOperations = in.nextInt();

		for(int z = 0; z<numOfOperations; z++) {
			System.out.print("Enter 0 for read and 1 for write: ");
			int operation = in.nextInt();
			
			if(operation==0) {
				System.out.print("Enter address: ");
				int address = in.nextInt();
				read(address, b, numSet, set, blocks);
			}
			else {
				System.out.print("Enter address: ");
				int address = in.nextInt();
				System.out.print("Enter value: ");
				int value = in.nextInt();
				write(address, value, b, numSet, set, blocks);
			}
		}
	}
	
	static void read(int address, int b, int numSet, int[][] set, int[][] blocks) {
		int blockNum = address/b;
		int setNum = blockNum%numSet;
		int[] cacheLines = set[setNum];
		boolean hi = false;
		for(int i = 0; i<cacheLines.length; i++) {
			if(cacheLines[i]==blockNum) {
				System.out.println("Cache hit!");
				hi = true;
				int[] toCheckOn = blocks[blockNum];
				q.add(blockNum);
				for(int j = 0; j<toCheckOn.length; j++) {
					if(blockNum*b+j==address) {
						System.out.println(toCheckOn[j]);
						printCache(set, blocks, numSet);
						break;
					}
				}
			}
		}
		if(!hi) {
			boolean ho = false;
			System.out.println("Cache miss!");
			printCache(set, blocks, numSet);
			for(int i = 0; i<cacheLines.length; i++) {
				if(cacheLines[i]==-1) {
					cacheLines[i] = blockNum;
					q.add(blockNum);
					ho = true;
					break;
				}
			}
			if(!ho) {
				if(q.isEmpty()) {
					cacheLines[0] = blockNum;
					q.add(0);
				}
				else {
					int x = q.poll();
					for(int p = 0; p<cacheLines.length; p++) {
						if(cacheLines[p]==x) {
							cacheLines[p] = blockNum;
							q.add(x);
							break;
						}
					}
				}
			}
		}
	}
	
	static void write(int address, int value, int b, int numSet, int[][] set, int[][] blocks) {
		int blockNum = address/b;
		int setNum = blockNum%numSet;
		int[] cacheLines = set[setNum];
		boolean hi = false;
		for(int i = 0; i<cacheLines.length; i++) {
			if(cacheLines[i]==blockNum) {
//				System.out.println("Cache hit!");
				hi = true;
				int[] toCheckOn = blocks[blockNum];
				q.add(blockNum);
				for(int j = 0; j<toCheckOn.length; j++) {
					if(blockNum*b+j==address) {
						toCheckOn[j] = value;
						hi = true;
						break;
					}
				}
			}
		}
		if(!hi) {
			boolean ho = false;
			for(int i = 0; i<cacheLines.length; i++) {
				if(cacheLines[i]==-1) {
					cacheLines[i] = blockNum;
					ho = true;
					q.add(blockNum);
					int[] toCheckOn = blocks[blockNum];
					for(int k = 0; k<toCheckOn.length; k++) {
						if(blockNum*b+k==address) {
							toCheckOn[k] = value;
							break;
						}
					}
					break;
				}
			}
			if(!ho) {
				if(q.isEmpty()) {
					cacheLines[0] = blockNum;
					q.add(0);
					int[] toCheckOn = blocks[blockNum];
					for(int k = 0; k<toCheckOn.length; k++) {
						if(blockNum*b+k==address) {
							toCheckOn[k] = value;
							break;
						}
					}
				}
				else {
					int x = q.poll();
					for(int p = 0; p<cacheLines.length; p++) {
						if(cacheLines[p]==x) {
							cacheLines[p] = blockNum;
							break;
						}
					}
					int[] toCheckOn = blocks[blockNum];
					for(int k = 0; k<toCheckOn.length; k++) {
						if(blockNum*b+k==address) {
							toCheckOn[k] = value;
							break;
						}
					}
					q.add(x);
				}
			}
		}
		printCache(set, blocks, numSet);
	}
	
	static void printCache(int[][] set, int[][] blocks, int numSet) {
		for(int i = 0; i<numSet; i++) {
			for(int j = 0; j<set[0].length; j++) {
				int blockNum = set[i][j];
				if(blockNum!=-1) {
					int[] toCheckOn = blocks[blockNum];
					for(int k = 0; k<toCheckOn.length; k++) {
						System.out.print(toCheckOn[k]+" ");
					}
				}
				else {
					for(int k = 0; k<blocks[0].length; k++) {
						System.out.print(0+" ");
					}
				}
				System.out.println();
			}
		}
	}
}
