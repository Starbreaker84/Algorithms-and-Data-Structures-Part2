class aBST
{
    public Integer Tree []; // массив ключей

    public aBST(int depth)
    {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = (int) Math.pow(2, (double) depth + 1) - 1;
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    private Integer findKey(int key, int index){
        if (index >= Tree.length) return null;
        if (Tree[index] == null) return -index;
        if (Tree[index] == key) return index;
        if (key < Tree[index]) return findKey(key, 2 * index + 1);
        return findKey(key, 2 * index + 2);
    }

    public Integer FindKeyIndex(int key)
    {
        return findKey(key, 0);
    }

    public int AddKey(int key)
    {
        // добавляем ключ в массив
        // индекс добавленного/существующего ключа или -1 если не удалось
        Integer keyIndex = FindKeyIndex(key);
        if (keyIndex == null) return -1;
        if (keyIndex >= 0 && Tree[0] != null) {
            return keyIndex;
        } else {
            Tree[Math.abs(keyIndex)] = key;
        }
        return Math.abs(keyIndex);
    }
}