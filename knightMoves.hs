import Data.List
import Control.Applicative

type KnightPos = (Int,Int)

-- Taken from Learn You A Haskell For Great Good!
moveKnight :: KnightPos -> [KnightPos]
moveKnight (c, r) = filter onBoard
    [(c+2,r-1),(c-2,r-1),(c+2,r+1),(c-2,r+1)
    ,(c-1,r+2),(c-1,r-2),(c+1,r-2),(c+1,r+2)
    ]
    where onBoard (c, r) = c `elem` [1..8] && r `elem` [1..8]

-- Rest is my own code

-- Recursive application of moveKnight, and concatenates the result into a single list
moveKnightList :: [KnightPos] -> [KnightPos]
moveKnightList [] = []
moveKnightList (x:xs) = (moveKnight x) ++ (moveKnightList xs)

-- Fail if start and end are the same tile;
-- otherwise, find the first element of the infinite list of move sequences that
-- contains the desired tile, adding 1 to give the correct number of moves
movesToReach :: KnightPos -> KnightPos -> Maybe Int
movesToReach start end
    | start == end = Nothing
    | otherwise    = (+) <$> Just 1 <*> (findIndex (elem end) (iterate moveKnightList (moveKnight start)))