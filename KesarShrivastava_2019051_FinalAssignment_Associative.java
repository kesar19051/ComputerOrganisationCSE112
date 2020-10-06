import java.util.*;

public class KesarShrivastava_2019051_FinalAssignment_Associative {
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
		int[] cache = new int[cl];
		for(int i = 0; i<cl; i++) {
			cache[i] = -1;
		}
		
		System.out.print("Enter b: ");
		int b = in.nextInt();
		int numOfBlock = numOfWords/b;
		int[][] blocks = new int[numOfBlock][b];
//		int[] timeStamp = new int[numOfBlock];
		
		System.out.print("Number of operations: ");
		int numOfOperations = in.nextInt();

		for(int z = 0; z<numOfOperations; z++) {
			System.out.print("Enter 0 for read and 1 for write: ");
			int operation = in.nextInt();
			
			if(operation==0) {
				System.out.print("Enter address: ");
				int address = in.nextInt();
				read(address, b, cl, cache, blocks);
			}
			else {
				System.out.print("Enter address: ");
				int address = in.nextInt();
				System.out.print("Enter value: ");
				int value = in.nextInt();
				write(address, value, b, cl, cache, blocks);
			}
		}
	}
	
	static void read(int address, int b, int cl, int[] cache, int[][] blocks) {
		boolean hi = false;
		int numBlock = address/b;
		int i = 0;
		while(i<cache.length) {
			if(cache[i] == numBlock) {
				System.out.println("Cache hit!"); 
				hi = true;
				int[] toCheckOn = blocks[numBlock];
				q.add(numBlock);
				for(int j = 0; j<toCheckOn.length; j++) {
					if(numBlock*b+j==address) {
						System.out.println(toCheckOn[j]);
						printCache(cache, blocks);
						break;
					}
				}
			}
			i++;
		}
		if(!hi) {
			boolean ho = false;
			System.out.println("Cache miss!");
			printCache(cache, blocks);
			int j = 0;
			while(j<cache.length) {
				if(cache[j]==-1) {
					cache[j] = numBlock;
					q.add(numBlock);
					ho = true;
					break;
				}
				j++;
			}
			if(!ho) {
				if(q.isEmpty()) {
					cache[0] = numBlock;
					q.add(0);
				}
				else {
					int x = q.poll();
					for(int p = 0; p<cache.length; p++) {
						if(cache[p]==x) {
							cache[p] = numBlock;
							break;
						}
					}
					q.add(x);
				}
			}
		}
	}
	
	static void write(int address, int value, int b, int cl, int[] cache, int[][] blocks) {
		boolean hi = false;
		int numBlock = address/b;
		int i = 0;
		while(i<cache.length) {
			if(cache[i] == numBlock) {
				int[] toCheckOn = blocks[numBlock];
				q.add(numBlock);
				for(int j = 0; j<toCheckOn.length; j++) {
					if(numBlock*b+j==address) {
						toCheckOn[j] = value;
						hi = true;
						break;
					}
				}
			}
			i++;
		}
		if(!hi) {
			boolean ho = false;
			int j = 0;
			while(j<cache.length) {
				if(cache[j]==-1) {
					cache[j] = numBlock;
					int[] toCheckOn = blocks[numBlock];
					q.add(numBlock);
					for(int k = 0; k<toCheckOn.length; k++) {
						if(numBlock*b+k==address) {
							toCheckOn[k] = value;
							ho = true;
							break;
						}
					}
					break;
				}
				j++;
			}
			if(!ho) {
				if(q.isEmpty()) {
					cache[0] = numBlock;
					q.add(0);
					int[] toCheckOn = blocks[numBlock];
					for(int k = 0; k<toCheckOn.length; k++) {
						if(numBlock*b+k==address) {
							toCheckOn[k] = value;
							break;
						}
					}
				}
				else {
					int x = q.poll();
					for(int p = 0; p<cache.length; p++) {
						if(cache[p]==x) {
							cache[p] = numBlock;
							break;
						}
					}
					int[] toCheckOn = blocks[numBlock];
					for(int k = 0; k<toCheckOn.length; k++) {
						if(numBlock*b+k==address) {
							toCheckOn[k] = value;
							break;
						}
					}
					q.add(x);
				}
			}
		}
		printCache(cache, blocks);
	}
	
	static void printCache(int[] cache, int[][] memory) {
		for(int i = 0; i<cache.length; i++) {
			int block = cache[i];
			if(block!=-1) {
				int[] words = memory[block];
				for(int j = 0; j<words.length; j++) {
					System.out.print(words[j]+" ");
				}
			}
			else {
				for(int j = 0; j<memory[0].length; j++) {
					System.out.print(0+" ");
				}
			}
			System.out.println();
		}
	}
}
