#include <iostream>
#include <cmath>
#define INF 200000;
using namespace std;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 돌을 던지는 사람의 수를 입력받는다.
 * 3. 각 사람들의 기록을 입력받는다.
 * 4. 기록의 절댓값을 구한다.
 * 5. 거리가 기존 최소거리보다 작으면 최소거리를 갱신하고 사람수를 1로 설정한다.
 * 6. 거리가 기존 최소거리와 같으면 사람수를 1 증가시킨다.
 * 7. 결과를 출력한다.
 */

int main(int argc, char** argv) {
	int testCase; // 테스트 케이스의 개수
	int humanCount; // 돌을 던지는 사람의 수
	int minDistance; // 0과 돌 사이 거리의 최소값
	int minDistanceCount; // 돌을 가장 가깝게 던진 사람들의 수

	
    cin >> testCase; // 1. 테스트 케이스의 개수를 입력받는다.
    
    for(int tc = 1; tc <= testCase; tc++) {
        // 2. 돌을 던지는 사람의 수를 입력받는다.
		cin >> humanCount;
			
        // 초기화
        minDistance = INF;
        minDistanceCount = 0;
        
        for(int human = 0; human < humanCount; human++) {
        	// 3. 각 사람들의 기록을 입력받는다.
	        int distance;
	        cin >> distance;
	        
	        // 4. 기록의 절댓값을 구한다.
	        distance = abs(distance);
	        
	        if(distance < minDistance) {
	            // 5. 거리가 기존 최소거리보다 작으면 최소거리를 갱신하고 사람수를 1로 설정한다.
	            minDistance = distance;
	            minDistanceCount = 1;
	        } else if(distance == minDistance) {
	            // 6. 거리가 기존 최소거리와 같으면 사람수를 1 증가시킨다.
	            minDistanceCount++;
	        }
		}


        // 7. 결과를 출력한다.
        cout << "#" << tc << " " << minDistance << " " << minDistanceCount << "\n";
    }
    
	return 0;
}
