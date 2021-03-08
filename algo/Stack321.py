def maxInOneList(arr, k):
    # 留下k个值。 35461 3
    stack =[]
    drop = len(arr) - k
    for i in range(0, len(arr)):

        while stack and drop and arr[i]>stack[-1]:
            stack.pop()
            drop = drop -1
            #print(stack)
        stack.append(arr[i])


        # print(arr[i], len(arr) - i - 1 + len(stack), stack)
        # if (arr[i] > stack[-1]):
        #     print(arr[i],len(arr) - i -1 + len(stack), stack)
        #     if (len(arr) - i -1 + len(stack) >= k):
        #         stack.pop()
        #     stack.append(arr[i])
        #     print(stack)
        #小于的情况，如果后面足够则忽略，不够则需要append

        #if(i==len(arr)-1 and len(stack)<k):stack.append(arr[i])
        #print("===done ",drop,i,stack)
    #print(stack)
    return stack[:k]

#maxInOneList("54321",3)

def maxNumber(nums1 , nums2, k: int) :
    #每个的最大k个值
    stack = []
    maxt = 0
    for i in range(k):
        res1= maxInOneList(nums1,i)
        res2 = maxInOneList(nums2,k-i)
        print(res1,res2)
        #merge
        res2idx = 0
        if res1==[] : stack = res2
        for res1idx in range(i):
            while res2idx<len(res2):
                if res1idx<i and res1[res1idx]> res2[res2idx]:
                    stack.append(res1[res1idx])
                    res1idx=res1idx+1
                else:
                    stack.append(res2[res2idx])
                    res2idx = res2idx+1
            if res1idx<i-1:
                stack.append(res1[res1idx])
        tmp = int(''.join([str(z) for z in stack]))
        maxt = max(maxt,tmp)
        print(maxt)
        stack=[]
    return maxt







maxNumber([3,4,6,5],[9,1,2,5,8,3],5)