package com.dsa.custom.tree.huffman;

import com.dsa.custom.list.List;

public class HuffTreeTest {

    // Build a Huffman tree from list huff-list
    static HuffTree buildTree(List<HuffTree> hufflist) {
        HuffTree tmp1;
        HuffTree tmp2;
        HuffTree tmp3;

        LettFreq tmpnode;
        for (hufflist.moveToPos(1); hufflist.length() > 1; hufflist.moveToPos(1)) {

            // While at least two items left
            hufflist.moveToStart();
            tmp1 = hufflist.remove();
            tmp2 = hufflist.remove();
            tmpnode = new LettFreq(tmp1.weight() + tmp2.weight());
            tmp3 = new HuffTree(tmpnode, tmp1, tmp2);

            // return to the list in sorted order
            for (hufflist.moveToStart();
                 hufflist.currPos() < hufflist.length();
                 hufflist.next())
                if (tmp3.weight() <= hufflist.getValue().weight()) {
                    hufflist.insert(tmp3);
                    break;
                }

            // Put in list
            if (hufflist.currPos() >= hufflist.length())
                hufflist.append(tmp3); // This is the heaviest value
        }

        hufflist.moveToStart();    // This is only tree on list
        return hufflist.remove();  // Return the tree
    }

}
