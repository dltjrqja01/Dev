#include<bits/stdc++.h>

using namespace std;

int main(){
    char sequence[1000000];
    cin>>sequence;
    stack<int> s;
    int index=0;
    int cnt=0;
    char a;
    while(sequence[index]){
        if(index==0) {
            a=sequence[index];
            cnt+=1;
        }
        else {
            if(a==sequence[index]){
                cnt+=1;
            }
            else {
                s.push(cnt);
                cnt=1;
                a=sequence[index];
            }
        }
        index++;
    }
    s.push(cnt);
    int Max=s.top();
    while(!s.empty()){
        int pop = s.top();
        if(Max<pop){
            Max = pop;
        }
        s.pop();
    }
    cout<<Max<<"\n";
}