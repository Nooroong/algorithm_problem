#include <stdio.h>
#include <string.h>

int isVowel(char c); // 해당 문자가 모음이라면 1을 반환, 자음이라면 0을 반환.

int main(void)
{
    char password[21] = {0};
    
    int condition1 = 0; // 조건1을 충족했는지에 대한 플래그
    int condition2 = 0; // 조건2를 충족했는지에 대한 플래그
    int condition3 = 0; // 조건3을 충족했는지에 대한 플래그
    
    int i;
    
    while(1)
    {
        scanf("%s", password);
    
        if(!strcmp(password, "end"))
            break;
        
        condition1 = 0; // 모음이 한번이라도 발견되면 1로 바뀜.
        condition2 = 1; // 조건을 한 번이라도 충족시키지 못하면 0으로 바뀜.
        condition3 = 1; // 위와 같음. 
        
        
        if(strlen(password) == 1)
    	{
    		// 한 문자만 입력된 경우, 그 문자는 무조건 모음이어야 한다.
    		
    		if(isVowel(password[0]))
    			printf("<%s> is acceptable.\n", password);
    		else
				printf("<%s> is not acceptable.\n", password);
				
			continue;
		}
		else if(strlen(password) == 2)
		{
			// 2글자만 입력된 경우에는 조건 1, 3만 보면 된다.
			
			if(isVowel(password[0]) || isVowel(password[1])) // 조건 1 
			{
	            if((password[0] != password[1] || password[0] == 'e') || password[0] == 'o') // 조건 3 
	                printf("<%s> is acceptable.\n", password);
	            else
	            	printf("<%s> is not acceptable.\n", password);
			}
			else
				printf("<%s> is not acceptable.\n", password);
		}
		else
		{
			// 원소를 3개씩 묶어서 살펴본다.
	        for(i = 0; i <= strlen(password)-3; i++)
	        {
	            // 조건 1: 모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
	            if(!condition1) // 모음을 한 번이라도 발견하면 매번 검사할 필요가 없음.
	            {
	            	if(isVowel(password[i]) || isVowel(password[i+1]) || isVowel(password[i+2]))
	                	condition1 = 1;
				}
	            
	            // 조건 2: 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
	            if(isVowel(password[i]) == isVowel(password[i+1]) &&
	               isVowel(password[i+1]) == isVowel(password[i+2]))
	                condition2 = 0;
	            
	            // 조건 3: 같은 글자가 연속적으로 두번 오면 안된다.
	            if(password[i] == password[i+1] || password[i+1] == password[i+2])
	            {
	                // 단, ee 와 oo는 허용한다.(어느경우에나 해당되는 i+1만 보면됨)
	                if(password[i+1] != 'e' && password[i+1] != 'o')
	                    condition3 = 0;
	            }
	        }
	        
	        // 세 조건을 모두 충족시켜야 높은 품질을 가진 비밀번호라 할 수 있다.
	        if(condition1 && condition2 && condition3)
	            printf("<%s> is acceptable.\n", password);
	        else
	            printf("<%s> is not acceptable.\n", password);
		}

    }
    
    return 0;
}

int isVowel(char c)
{
    if(c == 'a' || c == 'i' || c == 'o' ||
       c == 'u' || c == 'e')
        return 1;
    else
        return 0;
}