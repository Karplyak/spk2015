
function mutationChildren = ivert_with_landslip(parents ,options,NVARS, ...
    FitnessFcn, state, thisScore,thisPopulation,mutationRate)
%мутація обміну
mutationChildren = cell(length(parents),1);
for i=1:length(parents)
    parent = thisPopulation{parents(i)}; 
    %p = ceil(length(parent) * rand(1,2));
     p1 = randi([1 length(parent)],1,1);
     p2 = randi([p1 length(parent)],1,1);

    child = parent;
    %заміна генів р1 р2 
    tmp = child(p2);
    child(p2) = child(p1);
    child(p1) = tmp;
    
    mutationChildren{i} = child; 
end
end
