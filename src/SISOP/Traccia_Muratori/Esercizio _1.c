#import <stdio.h>

float** create( int, int);
void read(float**,int,int);
void print(float**,int,int);
int* verifica_vet_matrice(float**,int,int,int*);
int matGrande(float**,int,int);
int vetPiccolo(float**,int,int);

int main(){
	float** mat;
	int n;
	int m;
	int* res;
	float v={1.3,56.2,5.2,7.4,21.3}
	
	printf("Inserire il numero di righe > ");
	scanf("%d",&n);
	printf("Inserire il numeor di colonne >");
	scanf("%d",&m);
	mat=create(n,m);
	
	
	
	res=verifica_vet_matrice(mat,n,m,v);
	
	printf("matGrande è pari a: %d\n",res[0]);
	printf("matGrande è pari a: %d\n",res[1]);
	
	return 0;
}


float** create(int n,int m){
	float mat**;
	
	mat=(float**)malloc(sizeof(float*)*n);
	if(mat==NULL){
		printf("Malloc failure\n");
		free(mat);
		exit(1);
	}
	int i;
	int j;
	for(i=0;i<n;i++){
		mat[i]=(float*)malloc(sizeof(float)*m);
		if(mat[i]==NULL){
			int k;
			for(k=0;k<i;i++)
				free(mat[i]);
			free(mat);
			exit(1);
		}	
	}
	return mat;
}


void read(float** mat,int n,int m){
	int i;
	int j;
	
	for(i=0;i<n;i++){
		for(j=0;j<m;j++){
			printf("Inserisci l'elemento mat[%d][%d]",i,j);
			scanf("%f",&mat[i][j]);
		}
	}
	
}

void print(float** mat,int n,int m){
	
	int i;
	int j;
	
	for(i=0;i<n;i++){
		for(j=0;j<m;j++)
			printf("%f\t",mat[i][j]);
		printf("\n")
	}
	
}


int* verifica_vet_matrice(float** mat,int n,int m,float* v){
	
	int* res;
	res=(int*)malloc(sizeof(int)*2);
	if(res==NULL){
		printf("Malloc Failure");
		free(res);
		exit(1);
	}
	
	res[0]=matGrande(mat,n,m);
	res[1]=vetPiccolo(mat,n,m,v);
	
	return res;
}

int matGrande(float** mat,int n,int m){
	int i;
	int j;
	
	int count=0;
	
	for(i=0;i<n;i++){
		if(i%2==0 && i>0){
			int x=0;
			for(j=0;j<m;j++){
				if(mat[i][j]>mat[i-1][j]) x++;
			}
			if(x>m/2) count++;
		}
	}
	return count;
}

int vetPiccolo(float** mat,int n,int m,float v){
	int i;
	int j;
	int count=0;
	
	for(j=0;j<m;j++){
		if(j%2==0){
			int x=0;
			for(i=0;i<n;i++){
				if(mat[i][j]>v[i]) x++;
			}
			if(x>n/2) count++;
		}
	}
	return count;
}




