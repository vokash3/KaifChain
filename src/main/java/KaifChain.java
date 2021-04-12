import block.Block;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class KaifChain {

    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 1;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

//        blockChain.add(new Block("The First block", "0"));
//        blockChain.add(new Block("The Second block", blockChain.get(blockChain.size() - 1).hash));
//        blockChain.add(new Block("The Third block", blockChain.get(blockChain.size() - 1).hash));
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("Yo im the second block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Trying to Mine block 2... ");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("Hey im the third block",blockChain.get(blockChain.size()-1).hash));
        System.out.println("Trying to Mine block 3... ");
        blockChain.get(2).mineBlock(difficulty);

        for (int i = 0; i < blockChain.size(); i++){
            System.out.println("Trying to mine block " + (i+1));
            blockChain.get(i).mineBlock(difficulty);
        }

        System.out.println("\nBlockchain is Valid: " + isChainValid());
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(blockchainJson);

        long finish = System.currentTimeMillis();
        System.out.println("Execution time: " + (finish - start) + "ms");

    }

    public static Boolean isChainValid(){
        Block previousBlock;
        Block currentBlock;

        for(int i = 1; i < blockChain.size(); i++){
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get( i - 1);

            if(!currentBlock.previousHash.equals(previousBlock.hash)) {
                System.out.println("Current Hashes are not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes are not equal");
                return false;
            }
        }
        return true;
    }
}
