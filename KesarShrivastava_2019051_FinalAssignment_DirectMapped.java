
import java.util.*;
public class KesarShrivastava_2019051_FinalAssignment_DirectMapped {
	public static int flag = 0; //to check whether 0th block is in line 0
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter number of addresses: ");
		int numOfWords = in.nextInt();
//		System.out.println();
		
		System.out.print("Enter s: ");
		int s = in.nextInt(); //size of cache = cache lines*block size
//		System.out.println();
		
		System.out.print("Enter cl: ");
		int cl = in.nextInt(); //cache lines
//		System.out.println();
		int[] cache = new int[cl];
		for(int i = 0; i<cl; i++) {
			cache[i] = -1;
		}
		
		
		System.out.print("Enter b: ");
		int b = in.nextInt(); //block size should be in format that specifies how many words it contains
//		System.out.println();
		int numOfBlock = numOfWords/b;
		int[][] blocks = new int[numOfBlock][b];
		
		System.out.print("Number of operations: ");
		int numOfOperations = in.nextInt();
//		System.out.println();
		
		for(int z = 0; z<numOfOperations; z++) {
		System.out.print("Enter 0 for read and 1 for write: ");
		int operation = in.nextInt();
//		System.out.println();
		
		if(operation==0) {
			System.out.print("Enter address: ");
			int address = in.nextInt();
			read(address, b, cl, cache, blocks);
//			printCache(cache, blocks);
		}
		else {
			System.out.print("Enter address: ");
			int address = in.nextInt();
			System.out.print("Enter value: ");
			int value = in.nextInt();
			write(address, value, b, cl, cache, blocks);
//			printCache(cac1he, blocks);
		}}
	}
	
	static void read(int address, int b, int cl, int[] cache, int[][] blocks) {
		int blockNum = address/b;
		int lineInCache = blockNum%cl;
		
			if(cache[lineInCache]!=blockNum) { //this checks if the particular line in cache is empty
				System.out.println("Cache Miss!");
				printCache(cache, blocks);
				cache[lineInCache]=blockNum;
			}
			else {
				System.out.println("Cache Hit!");
				printCache(cache, blocks);
				int[] toCheckOn = blocks[blockNum];
				for(int i = 0; i<b; i++) {
					if(blockNum*b+i==address) {
						System.out.println(toCheckOn[i]);
					}
				}
			}
	}
	
	static void write(int address, int value, int b, int cl, int[] cache, int[][] blocks) {
		int blockNum = address/b;
		int lineInCache = blockNum%cl;
			if(cache[lineInCache]!=blockNum) { //this checks if the particular line in cache is empty
				cache[lineInCache]=blockNum;
				int[] toCheckOn = blocks[blockNum];
				for(int i = 0; i<b; i++) {
					if(blockNum*b+i==address) {
						toCheckOn[i] = value;
					}
				}
				printCache(cache, blocks);
			}
			else {
				int[] toCheckOn = blocks[blockNum];
				for(int i = 0; i<b; i++) {
					if(blockNum*b+i==address) {
						toCheckOn[i] = value;
					}
				}
				printCache(cache, blocks);
			}
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

