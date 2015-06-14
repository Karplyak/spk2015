
function xoverKids  = crossover_two_point_order(parents,options,NVARS, ...
    FitnessFcn,thisScore,thisPopulation)
%jlyjточкове впорядковуюче схрещування 
nKids = length(parents)/2;
xoverKids = cell(nKids,1); % Normally zeros(nKids,NVARS);
index = 1;
for i=1:nKids
    %вибір бітьків
    parent1 = thisPopulation{parents(index)};
    if(parents(index) == length(thisPopulation))
        parent2 = thisPopulation{parents(index)-1};
    else 
        parent2 = thisPopulation{parents(index)+1};
    end
    %генерування дві точки схрещування
     p1 = randi([0 length(parent1)-1],1,1);
     child = parent2;
     n = 0;
     %копіювання в нащадок між точками схрещування генів 2 
      %батька в упорядкованому вигляді зліва направо 
      
     child(p1+1)=parent1(p1+1) 
      
     for j = p1+1:length(parent2)       
        if all(child ~= parent2(j))
            child(p1+1+n) =  parent2(j);
            n = n+1;
        end
     end
     xoverKids{i} = child;
     index = index + 2;
end
