#include<bits/stdc++.h>

using namespace std;

int main(){
    long long n;
    cin>>n;
    long long arr[200001]={0,};
    for(int i=1;i<n;i++){
        int m;
        cin>>m;
        arr[m] = 1;
    }
    int index=1;
    while(arr[index]!=0){
        index++;
    }
    cout<<index<<"\n";
}