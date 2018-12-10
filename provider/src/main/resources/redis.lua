local totalNumber = ARGV[1]
local id = KEYS[1]
local jcount = redis.call('hget','order'..id,'jcount')
if jcount < totalNumber then
    return 1
end

local jcount = jcount - totalNumber
redis.call('hset','order'..id,'jcount',jcount)

local order = ARGV[2]
redis.call('rpush','miaosha'..id,order)

if jcount == 0 then
    return 2
else return 1
end